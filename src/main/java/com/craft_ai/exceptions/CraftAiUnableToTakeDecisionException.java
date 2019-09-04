package com.craft_ai.exceptions;

import java.util.List;

import com.craft_ai.interpreter.DecisionRule;

public class CraftAiUnableToTakeDecisionException extends RuntimeException {

  private static final long serialVersionUID = 169902577014177108L;

  private List<DecisionRule<?>> decisionRules;

  public CraftAiUnableToTakeDecisionException(String message, List<DecisionRule<?>> decisionRules) {
    super(String.format("Unable to take decision: %s.", message));
    this.decisionRules = decisionRules;
  }

  public List<DecisionRule<?>> getDecisionRules() {
    return decisionRules;
  }
}
