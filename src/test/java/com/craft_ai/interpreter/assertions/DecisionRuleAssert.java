package com.craft_ai.interpreter.assertions;

import com.craft_ai.interpreter.pojo.DecisionRule;
import org.assertj.core.api.AbstractAssert;

import java.util.Arrays;
import java.util.Objects;

public class DecisionRuleAssert extends AbstractAssert<DecisionRuleAssert, DecisionRule> {

  public DecisionRuleAssert(DecisionRule decisionRule) {
    super(decisionRule, DecisionRuleAssert.class);
  }

  public static DecisionRuleAssert assertThat(DecisionRule decisionRule) {
    return new DecisionRuleAssert(decisionRule);
  }

  public DecisionRuleAssert hasProperty(String property) {
    isNotNull();

    if (!Objects.equals(actual.getProperty(), property)) {
      failWithMessage("Expected property to be <%s> but was <%s>", property, actual.getProperty());
    }

    return this;
  }

  public DecisionRuleAssert hasOperator(String operator) {
    isNotNull();

    if (!Objects.equals(actual.getOperator(), operator)) {
      failWithMessage("Expected operator to be <%s> but was <%s>", operator, actual.getOperator());
    }

    return this;
  }

  public DecisionRuleAssert hasOperand(double... values) {
    isNotNull();

    if (!Arrays.equals(values, actual.getOperand())) {
      failWithMessage("Expected operand to be <%s> but was <%s>", Arrays.toString(values), actual.getOperand());
    }

    return this;
  }

}
