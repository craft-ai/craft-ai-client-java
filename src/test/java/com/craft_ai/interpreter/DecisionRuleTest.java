package com.craft_ai.interpreter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashMap;
import java.util.Map;

import com.craft_ai.exceptions.CraftAiInvalidContextException;

import org.junit.jupiter.api.Test;

public class DecisionRuleTest {
  private static final Map<String, Object> TEST_CONTEXT = new HashMap<String, Object>();
  static {
    TEST_CONTEXT.put("enum", "TOTO");
    TEST_CONTEXT.put("continuous", 5.5);
    TEST_CONTEXT.put("five", 5);
    TEST_CONTEXT.put("eleven", 11);
    TEST_CONTEXT.put("zero", 0);
    TEST_CONTEXT.put("ten", 10);
  }

  @Test
  public void test_equals_string() {
    assertThat(DecisionRule.create(Operator.IS, "enum", "TOTO").evaluate(TEST_CONTEXT)).isTrue();
    assertThat(DecisionRule.create(Operator.IS, "enum", "TITI").evaluate(TEST_CONTEXT)).isFalse();
  }

  @Test
  public void test_equals_numbers() {
    assertThat(DecisionRule.create(Operator.IS, "continuous", 5.5).evaluate(TEST_CONTEXT)).isTrue();
    assertThat(DecisionRule.create(Operator.IS, "continuous", 5.5d).evaluate(TEST_CONTEXT)).isTrue();
    assertThat(DecisionRule.create(Operator.IS, "continuous", -5.5).evaluate(TEST_CONTEXT)).isFalse();
  }

  @Test
  public void test_gte_numbers() {
    assertThat(DecisionRule.create(Operator.GTE, "continuous", 4.5).evaluate(TEST_CONTEXT)).isTrue();
    assertThat(DecisionRule.create(Operator.GTE, "continuous", 5.5).evaluate(TEST_CONTEXT)).isTrue();
    assertThat(DecisionRule.create(Operator.GTE, "continuous", 6.5).evaluate(TEST_CONTEXT)).isFalse();
  }

  @Test
  public void test_gte_strings() {
    assertThatExceptionOfType(CraftAiInvalidContextException.class)
        .isThrownBy(() -> DecisionRule.create(Operator.GTE, "enum", 4.5).evaluate(TEST_CONTEXT));
  }

  @Test
  public void test_lt_numbers() {
    assertThat(DecisionRule.create(Operator.LT, "continuous", 4.5).evaluate(TEST_CONTEXT)).isFalse();
    assertThat(DecisionRule.create(Operator.LT, "continuous", 5.5).evaluate(TEST_CONTEXT)).isFalse();
    assertThat(DecisionRule.create(Operator.LT, "continuous", 6.5).evaluate(TEST_CONTEXT)).isTrue();
  }

  @Test
  public void test_lt_strings() {
    assertThatExceptionOfType(CraftAiInvalidContextException.class)
        .isThrownBy(() -> DecisionRule.create(Operator.LT, "enum", 4.5).evaluate(TEST_CONTEXT));
  }

  @Test
  public void test_interval_numbers() throws Exception {
    assertThat(DecisionRule.create(Operator.IN, "five", new Interval(0, 10)).evaluate(TEST_CONTEXT)).isTrue();
    assertThat(DecisionRule.create(Operator.IN, "eleven", new Interval(0, 10)).evaluate(TEST_CONTEXT)).isFalse();
    assertThat(DecisionRule.create(Operator.IN, "zero", new Interval(0, 10)).evaluate(TEST_CONTEXT)).isTrue();
    assertThat(DecisionRule.create(Operator.IN, "ten", new Interval(0, 10)).evaluate(TEST_CONTEXT)).isFalse();
  }

}
