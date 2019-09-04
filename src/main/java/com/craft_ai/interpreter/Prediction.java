package com.craft_ai.interpreter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prediction {
  @JsonProperty("predicted_value")
  private Object predictedValue;
  @JsonProperty("confidence")
  private double confidence;
  @JsonProperty("standard_deviation")
  private double standardDeviation;
  @JsonProperty("decision_rules")
  private List<DecisionRule<?>> decisionRules;

  public Prediction() {
  }

  public Prediction(Node leaf, List<DecisionRule<?>> decisionRules) {
    this.predictedValue = leaf.getPredictedValue();
    this.confidence = leaf.getConfidence();
    this.standardDeviation = leaf.getStandardDeviation();
    this.decisionRules = decisionRules;
  }

  public Object getPredictedValue() {
    return predictedValue;
  }

  public void setPredictedValue(Object predictedValue) {
    this.predictedValue = predictedValue;
  }

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public double getStandardDeviation() {
    return standardDeviation;
  }

  public void setStandardDeviation(double standardDeviation) {
    this.standardDeviation = standardDeviation;
  }

  public List<DecisionRule<?>> getDecisionRules() {
    return decisionRules;
  }

  public void setDecisionRules(List<DecisionRule<?>> decisionRules) {
    this.decisionRules = decisionRules;
  }
}
