package com.craft_ai.interpreter.operators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OperatorsTest {

  @Test
  public void test_equals_string() {
    assertThat(Operators.equalOperator("TOTO", "TOTO")).isTrue();
    assertThat(Operators.equalOperator("TOTO", "TITI")).isFalse();
  }

  @Test
  public void test_equals_numbers() {
    assertThat(Operators.equalOperator(5.5, 5.5)).isTrue();
    assertThat(Operators.equalOperator(5.5, 5.5d)).isTrue();
    assertThat(Operators.equalOperator(5.5, -5.5)).isFalse();
  }

  @Test
  public void test_gte_numbers() {
    assertThat(Operators.greaterOperator(5.5, 4.5)).isTrue();
    assertThat(Operators.greaterOperator(5.5, 5.5)).isTrue();
    assertThat(Operators.greaterOperator(5.5, 6.5)).isFalse();
  }

  @Test
  public void test_gte_strings() {
    assertThat(Operators.greaterOperator("5.5", 4.5)).isFalse();
  }

  @Test
  public void test_lt_numbers() {
    assertThat(Operators.lessOperator(5.5, 4.5)).isFalse();
    assertThat(Operators.lessOperator(5.5, 5.5)).isFalse();
    assertThat(Operators.lessOperator(5.5, 6.5)).isTrue();
  }

  @Test
  public void test_lt_strings() {
    assertThat(Operators.lessOperator("5.5", 4.5)).isFalse();
  }

  @Test
  public void test_interval_numbers() throws Exception {
    assertThat(Operators.intervalOperator(5, 0, 10)).isTrue();
    assertThat(Operators.intervalOperator(11, 0, 10)).isFalse();
    assertThat(Operators.intervalOperator(0, 0, 10)).isTrue();
    assertThat(Operators.intervalOperator(10, 0, 10)).isFalse();
  }

}
