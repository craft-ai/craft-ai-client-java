package com.craft_ai.interpreter.decisionrule;

import com.craft_ai.interpreter.exceptions.Exception;
import com.craft_ai.interpreter.operators.Operators;
import com.craft_ai.interpreter.pojo.DecisionRule;

import java.util.Map;

public class EvaluateDecisionRule {

  public static boolean evalute(DecisionRule decisionRule, Map<String, ?> variables) {
    Object value = variables.get(decisionRule.getProperty());
    double[] operand = decisionRule.getOperand();

    switch (decisionRule.getOperator()) {
    case (Operators.IN): {
      return Operators.intervalOperator(value, operand[0], operand[1]);
    }
    case (Operators.GTE): {
      return Operators.greaterOperator(value, operand[0]);
    }
    case (Operators.IS): {
      return Operators.equalOperator(value, operand[0]);
    }
    case (Operators.LT): {
      return Operators.lessOperator(value, operand[0]);
    }
    default: {
      throw new Exception("Unknown operator '" + decisionRule.getOperator() + "'.");
    }
    }
  }

}
