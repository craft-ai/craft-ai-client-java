package com.craft_ai.interpreter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

import com.craft_ai.exceptions.CraftAiInvalidValueException;
import com.craft_ai.exceptions.CraftAiUnknownPropertyTypeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PropertyType {
  TIME_OF_DAY("time_of_day") {
    @Override
    protected boolean validateNumberValue(Number value) {
      return value.doubleValue() >= 0 && value.doubleValue() < 24;
    }
  },
  DAY_OF_MONTH("day_of_month") {
    @Override
    protected boolean validateNumberValue(Number value) {
      if (value instanceof Integer) {
        return value.intValue() >= 1 && value.intValue() <= 31;
      }
      return false;
    }
  },
  DAY_OF_WEEK("day_of_week") {
    @Override
    protected boolean validateNumberValue(Number value) {
      if (value instanceof Integer) {
        return value.intValue() >= 0 && value.intValue() <= 6;
      }
      return false;
    }
  },
  MONTH_OF_YEAR("month_of_year") {
    @Override
    protected boolean validateNumberValue(Number value) {
      if (value instanceof Integer) {
        return value.intValue() >= 1 && value.intValue() <= 12;
      }
      return false;
    }
  },
  TIMEZONE("timezone") {
    @Override
    protected boolean validateStringValue(String value) {
      int hour;
      int minute;

      if (VALID_TIME_ZONE.contains(value))
        return true;

      for (Pattern pattern : TIMEZONE_OFFSET) {
        Matcher matcher = pattern.matcher(value);

        if (matcher.matches()) {
          hour = Integer.valueOf(matcher.group("hour"));
          minute = matcher.groupCount() == 2 ? Integer.valueOf(matcher.group("minute")) : 0;

          if ((hour >= -12) && (hour <= 14) && (minute >= 0) && (minute <= 60)) {
            return true;
          }
        }
      }
      return false;
    }

    @Override
    protected boolean validateNumberValue(Number value) {
      int intValue = value.intValue();
      if (intValue != value.doubleValue()) {
        return false;
      }
      return intValue <= 840 && intValue >= -720;
    }
  },
  CONTINUOUS("continuous") {
    @Override
    protected boolean validateNumberValue(Number value) {
      return true;
    }
  },
  PERIODIC("periodic") {
    @Override
    protected boolean validateNumberValue(Number value) {
      return true;
    }
  },
  ENUM("enum") {
    @Override
    protected boolean validateStringValue(String value) {
      return true;
    }
  };

  private static final List<String> VALID_TIME_ZONE = Arrays.asList("UTC", "Z", "GMT", "BST", "IST", "WET", "WEST",
      "CET", "CEST", "EET", "EEST", "MSK", "MSD", "AST", "ADT", "EST", "EDT", "CST", "CDT", "MST", "MDT", "PST", "PDT",
      "HST", "AKST", "AKDT", "AEST", "AEDT", "ACST", "ACDT", "AWST");

  private final static Pattern[] TIMEZONE_OFFSET = new Pattern[] {
      Pattern.compile("^(?<hour>[\\+-]?\\d{2}):(?<minute>\\d{2})$"),
      Pattern.compile("^(?<hour>[\\+-]?\\d{2})(?<minute>\\d{2})$"), Pattern.compile("^(?<hour>[\\+-]?\\d{2})$") };

  private String label;

  PropertyType(String label) {
    this.label = label;
  }

  @JsonValue
  public String getLabel() {
    return this.label;
  }

  @Override
  public String toString() {
    return this.label;
  }

  public void validate(Object value) throws CraftAiInvalidValueException {
    if (!validateNoThrow(value)) {
      throw new CraftAiInvalidValueException(value, this);
    }
  }

  public void validate(String property, Object value) throws CraftAiInvalidValueException {
    if (!validateNoThrow(value)) {
      throw new CraftAiInvalidValueException(property, value, this);
    }
  }

  public boolean validateNoThrow(Object value) {
    if (value instanceof String) {
      return validateStringValue((String) value);
    } else if (value instanceof Number) {
      return validateNumberValue((Number) value);
    } else {
      return false;
    }
  }

  protected boolean validateStringValue(String value) {
    return false;
  }

  protected boolean validateNumberValue(Number value) {
    return false;
  }

  @JsonCreator
  public static PropertyType fromLabel(String label) {
    for (PropertyType propertyType : PropertyType.values()) {
      if (propertyType.getLabel().equals(label)) {
        return propertyType;
      }
    }
    throw new CraftAiUnknownPropertyTypeException(label);
  }
}
