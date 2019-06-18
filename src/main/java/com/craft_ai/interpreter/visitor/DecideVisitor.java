package com.craft_ai.interpreter.visitor;

import com.craft_ai.exceptions.CraftAiInvalidContextException;
import com.craft_ai.exceptions.CraftAiInvalidDecisionTreeException;
import com.craft_ai.exceptions.CraftAiUnableToTakeDecisionException;
import com.craft_ai.interpreter.Configuration;
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
    super.visit(decisionTree);
  }

  @Override
  public void visit(Configuration configuration) {
    outputProperty = (String) configuration.getOutput()[0];

    output.setContext(configuration.validate(output.getContext()));

    super.visit(configuration);
  }

  @Override
  public void visit(Node node) {
    if (node.isLeaf()) {
      Prediction prediction = new Prediction(node, decisionRules);

      if (prediction.getPredictedValue() == null) {
        throw new CraftAiUnableToTakeDecisionException(
            "the decision tree has no valid predicted value for the given context", decisionRules);
      }

      output.setPrediction(outputProperty, prediction);
    } else {
      DecisionRule<?> decisionRule = null;
      Map<String, ?> context = this.output.context;
      for (Node n : node.getChildren()) {
        decisionRule = n.getDecisionRule();
        if (decisionRule.evaluate(context)) {
          decisionRules.add(n.getDecisionRule());
          n.accept(this);
          return;
        }
      }
      throw new CraftAiUnableToTakeDecisionException(
          String.format("value '%s' for property '%s' doesn't validate any of the decision rules",
              context.get(decisionRule.getProperty()), decisionRule.getProperty()),
          decisionRules);
    }
  }
}
