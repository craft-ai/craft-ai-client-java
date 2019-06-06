package com.craft_ai.interpreter.decisiontree.visitor;

import com.craft_ai.interpreter.decisionrule.EvaluateDecisionRule;
import com.craft_ai.interpreter.pojo.Node;
import com.craft_ai.interpreter.pojo.Prediction;

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
    boolean evaluate;
    if (node.isLeaf()) {
      prediction = new Prediction();
      prediction.setPredictValue(node.getPredictedValue());
      prediction.setConfidence(node.getConfidence());
      prediction.setDecisionRule(node.getDecisionRule());
    } else {
      for (Node n : node.getChildren()) {
        evaluate = EvaluateDecisionRule.evalute(n.getDecisionRule(), context);

        if (evaluate) {
          n.accept(this);
          break;
        }
      }
    }
  }
}
