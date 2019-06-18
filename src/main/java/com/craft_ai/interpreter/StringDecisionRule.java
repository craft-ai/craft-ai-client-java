package com.craft_ai.interpreter;

import java.time.ZoneOffset;
import java.util.TimeZone;

public class StringDecisionRule extends DecisionRule<String> {
  public StringDecisionRule(String property, String operand) {
    super(Operator.IS, property, operand);
  }

  @Override
  protected boolean evaluateOnStringValue(String value) {
    if (operand.equals(value)) {
      return true;
    } else if (PropertyType.TIMEZONE.validateNoThrow(value)) {
      // It's a timezone we need to do some fancy stuffs
      ZoneOffset zoValue = ZoneOffset.of(value);
      ZoneOffset zoOperand = ZoneOffset.of(operand);
      return zoOperand.equals(zoValue);
    }
    return false;
  }

  @Override
  protected boolean evaluateOnNumberValue(Number value) {
    if (PropertyType.TIMEZONE.validateNoThrow(value)) {
      // It's a timezone we need to do some fancy stuffs
      ZoneOffset zoValue = ZoneOffset.ofTotalSeconds(value.intValue() * 60);
      ZoneOffset zoOperand = ZoneOffset.of(operand);
      return zoOperand.equals(zoValue);
    }
    return false;
  }
}
