package com.craft_ai.interpreter;

import com.craft_ai.exceptions.CraftAiUnknownOperatorException;

public enum Operator {
  GTE(">="), LT("<"), IN("[in["), IS("is");

  private String label;

  Operator(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  @Override
  public String toString() {
    return this.label;
  }

  public static Operator fromLabel(String label) {
    for (Operator operator : Operator.values()) {
      if (operator.getLabel().equals(label)) {
        return operator;
      }
    }
    throw new CraftAiUnknownOperatorException(label);
  }
}
