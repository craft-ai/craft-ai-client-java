package com.craft_ai.interpreter;

import java.util.Map;

import com.craft_ai.exceptions.CraftAiInvalidContextException;
import com.craft_ai.exceptions.CraftAiInvalidDecisionTreeException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = DecisionRuleDeserializer.class)
public abstract class DecisionRule<T> {
  protected Operator operator;
  protected String property;
  protected T operand;

  public DecisionRule(Operator operator, String property, T operand) {
    this.operator = operator;
    this.property = property;
    this.operand = operand;
  }

  public boolean evaluate(Map<String, ?> context) {
    Object value = context.get(property);
    if (value == null) {
      throw new CraftAiInvalidContextException(String.format("Undefined value for property '%s'.", property));
    }
    if (value instanceof String) {
      return evaluateOnStringValue((String) value);
    }
    if (value instanceof Number) {
      return evaluateOnNumberValue((Number) value);
    }
    throw new CraftAiInvalidContextException(
        String.format("Unexpected type for property '%s': values of type '%s' are not supported by operator '%s'.",
            property, value.getClass(), operator));
  }

  protected boolean evaluateOnStringValue(String value) {
    throw new CraftAiInvalidContextException(
        String.format("Unexpected type for property '%s': values of type 'String' are not supported by operator '%s'.",
            property, operator));
  }

  protected boolean evaluateOnNumberValue(Number value) {
    throw new CraftAiInvalidContextException(
        String.format("Unexpected type for property '%s': values of type 'Number' are not supported by operator '%s'.",
            property, operator));
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof DecisionRule<?>) {
      DecisionRule<?> castedOther = (DecisionRule<?>) other;
      return operator.equals(castedOther.operator) && property.equals(castedOther.property)
          && operand.equals(castedOther.operand);
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("context['%s'] %s %s", property, operator.getLabel(), operand);
  }

  public static DecisionRule<?> create(Operator operator, String property, Object operand) {
    if (operand instanceof Number) {
      if (!(operator.equals(Operator.GTE) || operator.equals(Operator.LT) || operator.equals(Operator.IS))) {
        throw new CraftAiInvalidDecisionTreeException(
            String.format("Invalid Decision Rule: '%s' is not a valid operator with a number operand.", operator));
      }
      return new NumberDecisionRule(property, operator, ((Number) operand));
    }
    if (operand instanceof String) {
      if (!operator.equals(Operator.IS)) {
        throw new CraftAiInvalidDecisionTreeException(
            String.format("Invalid Decision Rule: '%s' is not a valid operator with a string operand.", operator));
      }
      return new StringDecisionRule(property, (String) operand);
    }

    if (operand instanceof Interval) {
      if (!operator.equals(Operator.IN)) {
        throw new CraftAiInvalidDecisionTreeException(
            String.format("Invalid Decision Rule: '%s' is not a valid operator with an interval operand.", operator));
      }
      return new IntervalDecisionRule(property, (Interval) operand);
    }

    throw new CraftAiInvalidDecisionTreeException(String.format(
        "Invalid Decision Rule: A decision rule must have an operand that is either a number, a string or an interval."));
  }
}
