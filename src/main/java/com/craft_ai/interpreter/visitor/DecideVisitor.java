package com.craft_ai.interpreter.visitor;

import com.craft_ai.interpreter.DecideOutput;
import com.craft_ai.interpreter.DecisionRule;
import com.craft_ai.interpreter.DecisionTree;
import com.craft_ai.interpreter.Node;
import com.craft_ai.interpreter.Prediction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DecideVisitor extends DecisionTreeVisitorAdapter {

  private DecideOutput output;
  private String outputProperty;
  private List<DecisionRule<?>> decisionRules;

  public DecideVisitor(Map<String, ?> context) {
    this.output = new DecideOutput();
    this.output.setContext(context);
    this.decisionRules = new ArrayList<DecisionRule<?>>();
  }

  public DecideOutput getOutput() {
    return output;
  }

  @Override
  public void visit(DecisionTree decisionTree) {
    output.setVersion(decisionTree.getVersion());
    outputProperty = (String) decisionTree.getTrees().keySet().toArray()[0];
    super.visit(decisionTree);
  }

  @Override
  public void visit(Node node) {
    if (node.isLeaf()) {
      Prediction prediction = new Prediction(node, decisionRules);
      output.setPrediction(outputProperty, prediction);
    } else {
      for (Node n : node.getChildren()) {
        if (n.getDecisionRule().evaluate(this.output.context)) {
          decisionRules.add(n.getDecisionRule());
          n.accept(this);
          break;
        }
      }
    }
  }
}
