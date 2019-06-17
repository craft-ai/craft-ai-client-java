package com.craft_ai.interpreter;

import java.io.IOException;

import com.craft_ai.exceptions.CraftAiInvalidDecisionTreeException;
import com.craft_ai.exceptions.CraftAiUnknownOperatorException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class DecisionRuleDeserializer extends JsonDeserializer<DecisionRule<?>> {
  @Override
  public DecisionRule<?> deserialize(JsonParser jp, DeserializationContext context) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    String operatorString = node.get("operator").asText();
    Operator operator = null;
    try {
      operator = Operator.fromLabel(operatorString);
    } catch (CraftAiUnknownOperatorException e) {
      context.reportWrongTokenException(DecisionRule.class, node.asToken(), e.getMessage());
      return null;
    }

    JsonNode operandNode = node.get("operand");

    String property = node.get("property").asText();

    try {
      if (operandNode.isNumber()) {
        return DecisionRule.create(operator, property, operandNode.asDouble());
      }
      if (operandNode.isTextual()) {
        return DecisionRule.create(operator, property, operandNode.asText());
      }
      if (operandNode.isArray() && operandNode.size() == 2 && operandNode.get(0).isNumber()
          && operandNode.get(1).isNumber()) {
        return DecisionRule.create(operator, property,
            new Interval(operandNode.get(0).asDouble(), operandNode.get(1).asDouble()));
      }
      context.reportWrongTokenException(DecisionRule.class, node.asToken(),
          "Invalid Decision Rule: A decision rule must have an operand that is either a number, a string or an array of two numbers");
      return null;
    } catch (CraftAiInvalidDecisionTreeException e) {
      context.reportWrongTokenException(DecisionRule.class, node.asToken(), e.getMessage());
      return null;
    }
  }
}
