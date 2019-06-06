package com.craft_ai.interpreter.pojo;

public class DecisionRule {

  private double[] operand;
  private String operator;
  private String property;

  public double[] getOperand() {
    return operand;
  }

  public void setOperand(double[] operand) {
    this.operand = operand;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

}
