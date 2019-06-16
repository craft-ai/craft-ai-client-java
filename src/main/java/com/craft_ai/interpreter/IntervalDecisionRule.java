package com.craft_ai.interpreter;

public class IntervalDecisionRule extends DecisionRule {
  private Interval operand;

  public IntervalDecisionRule(String property, Interval operand) {
    super(Operator.IN, property);
    this.operand = operand;
  }

  @Override
  protected boolean evaluateOnNumberValue(Number value) {
    double doubleValue = value.doubleValue();
    if (operand.getLowerBound() < operand.getUpperBound()) {
      return (doubleValue >= operand.getLowerBound() && doubleValue < operand.getUpperBound());
    } else {
      return (doubleValue >= operand.getLowerBound() || doubleValue < operand.getUpperBound());
    }
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof IntervalDecisionRule) {
      IntervalDecisionRule castedOther = (IntervalDecisionRule) other;
      return operator.equals(castedOther.operator) && property.equals(castedOther.property)
          && operand.equals(castedOther.operand);
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("context['%s'] %s [%s, %s[", property, operator.getLabel(), operand.getLowerBound(),
        operand.getUpperBound());
  }
}
