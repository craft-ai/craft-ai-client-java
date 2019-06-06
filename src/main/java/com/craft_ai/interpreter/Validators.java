package com.craft_ai.interpreter;

import com.craft_ai.interpreter.exceptions.Exception;
import com.craft_ai.interpreter.exceptions.InvalidContextValueException;
import com.craft_ai.interpreter.operators.Operators;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

  private static final List<String> VALID_TIME_ZONE = Arrays.asList("UTC", "Z", "GMT", "BST", "IST", "WET", "WEST",
      "CET", "CEST", "EET", "EEST", "MSK", "MSD", "AST", "ADT", "EST", "EDT", "CST", "CDT", "MST", "MDT", "PST", "PDT",
      "HST", "AKST", "AKDT", "AEST", "AEDT", "ACST", "ACDT", "AWST");

  private final static Pattern[] TIMEZONE_OFFSET = new Pattern[] {
      Pattern.compile("^(?<hour>[\\+-]?\\d{2}):(?<minute>\\d{2})$"),
      Pattern.compile("^(?<hour>[\\+-]?\\d{2})(?<minute>\\d{2})$"), Pattern.compile("^(?<hour>[\\+-]?\\d{2})$") };

  public static void validateContinous(Object value) {
    if (!Operators.isNumberType(value)) {
      throw new InvalidContextValueException(value, PropertyType.CONTINOUS);
    }
  }

  public static void validateEnumeration(Object value) {
    if (!(value instanceof String) && !(value instanceof Boolean)) {
      throw new InvalidContextValueException(value, PropertyType.ENUMERATION);
    }
  }

  public static void validateTimezone(String timezone) {
    int hour;
    int minute;

    if (VALID_TIME_ZONE.contains(timezone))
      return;

    for (Pattern pattern : TIMEZONE_OFFSET) {
      Matcher matcher = pattern.matcher(timezone);

      if (matcher.matches()) {
        hour = Integer.valueOf(matcher.group("hour"));
        minute = matcher.groupCount() == 2 ? Integer.valueOf(matcher.group("minute")) : 0;

        if ((hour >= -12) && (hour <= 14) && (minute >= 0) && (minute <= 60)) {
          return;
        }
      }
    }

    throw new InvalidContextValueException(timezone, PropertyType.TIMEZONE);
  }

  public static void validateDayOfMonth(Object value) {
    validateBetween(value, 1, 31, PropertyType.DAY_OF_MONTH);
  }

  public static void validateMonthOfYear(Object value) {
    validateBetween(value, 1, 12, PropertyType.MONTH_OF_YEAR);
  }

  public static void validateDayOfWeek(Object value) {
    validateBetween(value, 0, 6, PropertyType.DAY);
  }

  private static void validateBetween(Object object, int min, int max, PropertyType type) {
    if (object instanceof Integer) {
      Integer value = ((Integer) object);
      if (value >= min && value <= max) {
        return;
      }
    }

    throw new InvalidContextValueException(object, type, String.format("in [%s, %s]", min, max));
  }

  public static void validateTimeOfDay(Object value) {
    float timeOfDay = -1;

    if (value instanceof String) {
      timeOfDay = Float.valueOf((String) value);
    } else if (value instanceof Number) {
      timeOfDay = ((Number) value).floatValue();
    }

    if (timeOfDay < 0f || timeOfDay > 24f) {
      throw new InvalidContextValueException(value, PropertyType.TIME_OF_DAY, "in (0.0, 24.0)");
    }
  }

  public static void validateValue(Object value, String type) {
    if (value != null) {
      // day of month
      if (type.equals(PropertyType.DAY_OF_MONTH.toString())) {
        Validators.validateDayOfMonth(value);
      } else if (type.equals(PropertyType.TIME_OF_DAY.toString())) { // time_of_day
        Validators.validateTimeOfDay(value);
      } else if (type.equals(PropertyType.DAY.toString())) { // day_of_week
        Validators.validateDayOfWeek(value);
      } else if (type.equals(PropertyType.MONTH_OF_YEAR.toString())) { // month_of_year
        Validators.validateMonthOfYear(value);
      } else if (type.equals(PropertyType.TIMEZONE.toString())) { // timeZone
        Validators.validateTimezone((String) value);
      } else if (type.equals(PropertyType.ENUMERATION.toString())) {
        Validators.validateEnumeration(value);
      } else if (type.equals(PropertyType.CONTINOUS.toString())) {
        Validators.validateContinous(value);
      }
    } else {
      // throw new Exception("ERROR property:" + inputEntity.getInputName() + " with
      // type: :" + inputEntity.getInputType() + " not defined");
    }
  }
}
