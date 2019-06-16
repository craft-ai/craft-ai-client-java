package com.craft_ai.interpreter;

public class NumberDecisionRule extends DecisionRule {
  private double operand;

  public NumberDecisionRule(String property, Operator operator, double operand) {
    super(operator, property);
    this.operand = operand;
  }

  @Override
  protected boolean evaluateOnNumberValue(Number value) {
    double doubleValue = value.doubleValue();
    switch (operator) {
    case GTE:
      return doubleValue >= operand;
    case LT:
      return doubleValue < operand;
    case IS:
      return doubleValue == operand;
    default:
      return super.evaluateOnNumberValue(value);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof NumberDecisionRule) {
      NumberDecisionRule castedOther = (NumberDecisionRule) other;
      return operator.equals(castedOther.operator) && property.equals(castedOther.property)
          && operand == castedOther.operand;
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("context['%s'] %s %s", property, operator.getLabel(), operand);
  }
}
