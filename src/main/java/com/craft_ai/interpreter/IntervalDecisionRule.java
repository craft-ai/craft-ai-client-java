package com.craft_ai.interpreter;

public class IntervalDecisionRule extends DecisionRule<Interval> {
  public IntervalDecisionRule(String property, Interval operand) {
    super(Operator.IN, property, operand);
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
}
