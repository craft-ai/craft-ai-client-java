package com.craft_ai.interpreter;

import com.craft_ai.exceptions.CraftAiInvalidValueException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PropertyTypeTest {

  @Test
  public void test_is_continous_value() {
    assertThatCode(() -> PropertyType.CONTINUOUS.validate(25)).doesNotThrowAnyException();
  }

  @Test
  public void test_is_not_continous_value() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.CONTINUOUS.validate("25"))
        .withMessage("'25' is an invalid value for type 'continuous', expected values are numbers.");
  }

  @Test
  public void test_boolean_is_not_enum_value() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class).isThrownBy(() -> PropertyType.ENUM.validate(true))
        .withMessage("'true' is an invalid value for type 'enum', expected values are strings.");
  }

  @Test
  public void test_string_is_enum_value() {
    assertThatCode(() -> PropertyType.ENUM.validate("string")).doesNotThrowAnyException();
  }

  @Test
  public void test_number_is_not_enum_value() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class).isThrownBy(() -> PropertyType.ENUM.validate(125))
        .withMessage("'125' is an invalid value for type 'enum', expected values are strings.");
  }

  @Test
  public void test_day_of_month() {
    assertThatCode(() -> PropertyType.DAY_OF_MONTH.validate(15)).doesNotThrowAnyException();
  }

  @Test
  public void test_day_of_month_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.DAY_OF_MONTH.validate(125))
        .withMessage("'125' is an invalid value for type 'day_of_month', expected values are integers in [1, 31].");
  }

  @Test
  public void test_day_of_month_with_wrong_type() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.DAY_OF_MONTH.validate("25"))
        .withMessage("'25' is an invalid value for type 'day_of_month', expected values are integers in [1, 31].");
  }

  @Test
  public void test_time_of_day() {
    assertThatCode(() -> PropertyType.TIME_OF_DAY.validate(15.5f)).doesNotThrowAnyException();
  }

  @Test
  public void test_time_of_day_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.TIME_OF_DAY.validate(125))
        .withMessage("'125' is an invalid value for type 'time_of_day', expected values are in [0, 24[.");
  }

  @Test
  public void test_time_of_day_with_wrong_type() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.TIME_OF_DAY.validate("25"))
        .withMessage("'25' is an invalid value for type 'time_of_day', expected values are in [0, 24[.");
  }

  @Test
  public void test_timezone_region_id() {
    assertThatCode(() -> PropertyType.TIMEZONE.validate("CEST")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset() {
    assertThatCode(() -> PropertyType.TIMEZONE.validate("01:00")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset_negative() {
    assertThatCode(() -> PropertyType.TIMEZONE.validate("-01:00")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset_condensed() {
    assertThatCode(() -> PropertyType.TIMEZONE.validate("0100")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset_condensed_negative() {
    assertThatCode(() -> PropertyType.TIMEZONE.validate("-0100")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_hour() {
    assertThatCode(() -> PropertyType.TIMEZONE.validate("-01")).doesNotThrowAnyException();
  }

  @Test
  public void test_hours_of_out_bound() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.TIMEZONE.validate("-25"))
        .withMessage("'-25' is an invalid value for type 'timezone', expected values are valid timezone descriptors.");
  }

  @Test
  public void test_minutes_of_out_bound() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.TIMEZONE.validate("15:75")).withMessage(
            "'15:75' is an invalid value for type 'timezone', expected values are valid timezone descriptors.");
  }

  @Test
  public void test_day_of_week() {
    assertThatCode(() -> PropertyType.DAY_OF_WEEK.validate(5)).doesNotThrowAnyException();
  }

  @Test
  public void test_day_of_week_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.DAY_OF_WEEK.validate(-5))
        .withMessage("'-5' is an invalid value for type 'day_of_week', expected values are integers in [0, 6].");
  }

  @Test
  public void test_month_of_year() {
    assertThatCode(() -> PropertyType.MONTH_OF_YEAR.validate(5)).doesNotThrowAnyException();
  }

  @Test
  public void test_month_of_year_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.MONTH_OF_YEAR.validate(-5))
        .withMessage("'-5' is an invalid value for type 'month_of_year', expected values are integers in [1, 12].");
  }

}
