package com.craft_ai.interpreter.visitor;

import com.craft_ai.interpreter.Node;
import com.craft_ai.interpreter.Prediction;

import java.util.Map;

public class InterpreterVisitor extends DecisionTreeVisitorAdapter {

  private Prediction prediction;
  private Map<String, ?> context;

  public InterpreterVisitor(Map<String, ?> context) {
    this.context = context;
  }

  public Prediction getPrediction() {
    return prediction;
  }

  @Override
  public void visit(Node node) {
    if (node.isLeaf()) {
      prediction = new Prediction();
      prediction.setPredictValue(node.getPredictedValue());
      prediction.setConfidence(node.getConfidence());
      prediction.setDecisionRule(node.getDecisionRule());
    } else {
      for (Node n : node.getChildren()) {
        if (n.getDecisionRule().evaluate(context)) {
          n.accept(this);
          break;
        }
      }
    }
  }
}
