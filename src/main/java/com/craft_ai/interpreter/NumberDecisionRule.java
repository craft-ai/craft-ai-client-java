package com.craft_ai.interpreter;

public class NumberDecisionRule extends DecisionRule<Number> {
  public NumberDecisionRule(String property, Operator operator, Number operand) {
    super(operator, property, operand);
  }

  @Override
  protected boolean evaluateOnNumberValue(Number value) {
    switch (operator) {
    case GTE:
      return value.doubleValue() >= operand.doubleValue();
    case LT:
      return value.doubleValue() < operand.doubleValue();
    case IS:
      return value.doubleValue() == operand.doubleValue();
    default:
      return super.evaluateOnNumberValue(value);
    }
  }
}
