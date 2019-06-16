package com.craft_ai.interpreter;

public class Prediction {

  private double confidence;
  private Object predictValue;
  private DecisionRule decisionRule;

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public Object getPredictValue() {
    return predictValue;
  }

  public void setPredictValue(Object predictValue) {
    this.predictValue = predictValue;
  }

  public DecisionRule getDecisionRule() {
    return decisionRule;
  }

  public void setDecisionRule(DecisionRule decisionRule) {
    this.decisionRule = decisionRule;
  }
}
