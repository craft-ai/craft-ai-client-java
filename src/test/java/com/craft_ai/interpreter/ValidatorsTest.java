package com.craft_ai.interpreter;

import com.craft_ai.interpreter.exceptions.Exception;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ValidatorsTest {

  @Test
  public void test_is_continous_value() {
    assertThatCode(() -> Validators.validateContinous(25)).doesNotThrowAnyException();
  }

  @Test
  public void test_is_not_continous_value() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateContinous("25"))
        .withMessage("'25' is an invalid value for type 'continuous'.");
  }

  @Test
  public void test_is_boolean_enumeration_value() {
    assertThatCode(() -> Validators.validateEnumeration(true)).doesNotThrowAnyException();
  }

  @Test
  public void test_is_string_enumeration_value() {
    assertThatCode(() -> Validators.validateEnumeration("string")).doesNotThrowAnyException();
  }

  @Test
  public void test_number_is_not_enumeration_value() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateEnumeration(125))
        .withMessage("'125' is an invalid value for type 'enum'.");
  }

  @Test
  public void test_day_of_month() {
    assertThatCode(() -> Validators.validateDayOfMonth(15)).doesNotThrowAnyException();
  }

  @Test
  public void test_day_of_month_out_of_bounds() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateDayOfMonth(125))
        .withMessage("'125' is an invalid value for type 'day_of_month', expected values are in [1, 31].");
  }

  @Test
  public void test_day_of_month_with_wrong_type() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateDayOfMonth("25"))
        .withMessage("'25' is an invalid value for type 'day_of_month', expected values are in [1, 31].");
  }

  @Test
  public void test_time_of_day() {
    assertThatCode(() -> Validators.validateTimeOfDay(15.5f)).doesNotThrowAnyException();
  }

  @Test
  public void test_time_of_day_out_of_bounds() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateTimeOfDay(125))
        .withMessage("'125' is an invalid value for type 'time_of_day', expected values are in (0.0, 24.0).");
  }

  @Test
  public void test_time_of_day_with_wrong_type() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateTimeOfDay("25"))
        .withMessage("'25' is an invalid value for type 'time_of_day', expected values are in (0.0, 24.0).");
  }

  @Test
  public void test_timezone_region_id() {
    assertThatCode(() -> Validators.validateTimezone("CEST")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset() {
    assertThatCode(() -> Validators.validateTimezone("01:00")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset_negative() {
    assertThatCode(() -> Validators.validateTimezone("-01:00")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset_condensed() {
    assertThatCode(() -> Validators.validateTimezone("0100")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_offset_condensed_negative() {
    assertThatCode(() -> Validators.validateTimezone("-0100")).doesNotThrowAnyException();
  }

  @Test
  public void test_timezone_region_hour() {
    assertThatCode(() -> Validators.validateTimezone("-01")).doesNotThrowAnyException();
  }

  @Test
  public void test_hours_of_out_bound() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateTimezone("-25"))
        .withMessage("'-25' is an invalid value for type 'timezone'.");
  }

  @Test
  public void test_minutes_of_out_bound() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateTimezone("15:75"))
        .withMessage("'15:75' is an invalid value for type 'timezone'.");
  }

  @Test
  public void test_day_of_week() {
    assertThatCode(() -> Validators.validateDayOfWeek(5)).doesNotThrowAnyException();
  }

  @Test
  public void test_day_of_week_out_of_bounds() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateDayOfWeek(-5))
        .withMessage("'-5' is an invalid value for type 'day_of_week', expected values are in [0, 6].");
  }

  @Test
  public void test_month_of_year() {
    assertThatCode(() -> Validators.validateMonthOfYear(5)).doesNotThrowAnyException();
  }

  @Test
  public void test_month_of_year_out_of_bounds() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> Validators.validateMonthOfYear(-5))
        .withMessage("'-5' is an invalid value for type 'month_of_year', expected values are in [1, 12].");
  }

}
