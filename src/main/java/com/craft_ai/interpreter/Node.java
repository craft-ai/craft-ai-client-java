package com.craft_ai.interpreter;

import com.craft_ai.interpreter.visitor.DecisionTreeVisitor;

import java.util.List;

public class Node {

  private double confidence;
  private DecisionRule<?> decisionRule;
  private String predictedValue;

  private List<Node> children;

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  public DecisionRule<?> getDecisionRule() {
    return decisionRule;
  }

  public void setDecisionRule(DecisionRule<?> decisionRule) {
    this.decisionRule = decisionRule;
  }

  public String getPredictedValue() {
    return predictedValue;
  }

  public void setPredictedValue(String predictedValue) {
    this.predictedValue = predictedValue;
  }

  public List<Node> getChildren() {
    return children;
  }

  public void setChildren(List<Node> children) {
    this.children = children;
  }

  public void accept(DecisionTreeVisitor decisionTreeVisitor) {
    decisionTreeVisitor.visit(this);
  }

  public boolean isLeaf() {
    return predictedValue != null;
  }

  public boolean isRoot() {
    return decisionRule == null;
  }

}
