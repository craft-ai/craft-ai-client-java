package com.craft_ai.interpreter;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.craft_ai.exceptions.CraftAiInvalidValueException;

import org.junit.jupiter.api.Test;

public class PropertyTypeTest {

  @Test
  public void test_is_continous_value() {
    assertThatCode(() -> PropertyType.CONTINUOUS.validate(25)).doesNotThrowAnyException();
  }

  @Test
  public void test_is_not_continous_value() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.CONTINUOUS.validate("25"))
        .withMessage("'25' is not a valid value of type 'continuous'.");
  }

  @Test
  public void test_boolean_is_not_enum_value() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class).isThrownBy(() -> PropertyType.ENUM.validate(true))
        .withMessage("'true' is not a valid value of type 'enum'.");
  }

  @Test
  public void test_string_is_enum_value() {
    assertThatCode(() -> PropertyType.ENUM.validate("string")).doesNotThrowAnyException();
  }

  @Test
  public void test_number_is_not_enum_value() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class).isThrownBy(() -> PropertyType.ENUM.validate(125))
        .withMessage("'125' is not a valid value of type 'enum'.");
  }

  @Test
  public void test_day_of_month() {
    assertThatCode(() -> PropertyType.DAY_OF_MONTH.validate(15)).doesNotThrowAnyException();
  }

  @Test
  public void test_day_of_month_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.DAY_OF_MONTH.validate(125))
        .withMessage("'125' is not a valid value of type 'day_of_month'.");
  }

  @Test
  public void test_day_of_month_with_wrong_type() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.DAY_OF_MONTH.validate("25"))
        .withMessage("'25' is not a valid value of type 'day_of_month'.");
  }

  @Test
  public void test_time_of_day() {
    assertThatCode(() -> PropertyType.TIME_OF_DAY.validate(15.5f)).doesNotThrowAnyException();
  }

  @Test
  public void test_time_of_day_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.TIME_OF_DAY.validate(125))
        .withMessage("'125' is not a valid value of type 'time_of_day'.");
  }

  @Test
  public void test_time_of_day_with_wrong_type() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.TIME_OF_DAY.validate("25"))
        .withMessage("'25' is not a valid value of type 'time_of_day'.");
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
        .withMessage("'-25' is not a valid value of type 'timezone'.");
  }

  @Test
  public void test_minutes_of_out_bound() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.TIMEZONE.validate("15:75"))
        .withMessage("'15:75' is not a valid value of type 'timezone'.");
  }

  @Test
  public void test_day_of_week() {
    assertThatCode(() -> PropertyType.DAY_OF_WEEK.validate(5)).doesNotThrowAnyException();
  }

  @Test
  public void test_day_of_week_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.DAY_OF_WEEK.validate(-5))
        .withMessage("'-5' is not a valid value of type 'day_of_week'.");
  }

  @Test
  public void test_month_of_year() {
    assertThatCode(() -> PropertyType.MONTH_OF_YEAR.validate(5)).doesNotThrowAnyException();
  }

  @Test
  public void test_month_of_year_out_of_bounds() {
    assertThatExceptionOfType(CraftAiInvalidValueException.class)
        .isThrownBy(() -> PropertyType.MONTH_OF_YEAR.validate(-5))
        .withMessage("'-5' is not a valid value of type 'month_of_year'.");
  }

}
