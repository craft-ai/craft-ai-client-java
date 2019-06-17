package com.craft_ai.interpreter.visitor;

import com.craft_ai.exceptions.CraftAiInvalidDecisionTreeException;
import com.craft_ai.interpreter.Configuration;
import com.craft_ai.interpreter.DecisionTree;
import com.craft_ai.interpreter.Node;

import java.util.Map;

public class DecisionTreeVisitorAdapter implements DecisionTreeVisitor {

  @Override
  public void visit(DecisionTree decisionTree) {
    visit(decisionTree.getConfiguration());

    if (decisionTree.getTrees() == null || decisionTree.getTrees().size() != 1) {
      throw new CraftAiInvalidDecisionTreeException("No support for multiple decision trees");
    }

    for (Map.Entry<String, Node> entry : decisionTree.getTrees().entrySet()) {
      entry.getValue().accept(this);
    }
  }

  @Override
  public void visit(Configuration configuration) {
  }

  @Override
  public void visit(Node node) {
  }
}
