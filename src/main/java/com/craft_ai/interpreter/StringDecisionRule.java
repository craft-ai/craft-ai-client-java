package com.craft_ai.interpreter;

public class StringDecisionRule extends DecisionRule<String> {
  public StringDecisionRule(String property, String operand) {
    super(Operator.IS, property, operand);
  }

  @Override
  protected boolean evaluateOnStringValue(String value) {
    return value.equals(operand);
  }
}
