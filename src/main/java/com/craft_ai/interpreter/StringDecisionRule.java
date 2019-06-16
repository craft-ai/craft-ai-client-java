package com.craft_ai.interpreter;

public class StringDecisionRule extends DecisionRule {
  private String operand;

  public StringDecisionRule(String property, String operand) {
    super(Operator.IS, property);
    this.operand = operand;
  }

  @Override
  protected boolean evaluateOnStringValue(String value) {
    return value.equals(operand);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof StringDecisionRule) {
      StringDecisionRule castedOther = (StringDecisionRule) other;
      return operator.equals(castedOther.operator) && property.equals(castedOther.property)
          && operand.equals(castedOther.operand);
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("context['%s'] %s '%s'", property, operator.getLabel(), operand);
  }
}
