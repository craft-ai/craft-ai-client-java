package com.craft_ai.interpreter.decisiontree.visitor;

import com.craft_ai.interpreter.exceptions.Exception;
import com.craft_ai.interpreter.pojo.Configuration;
import com.craft_ai.interpreter.pojo.DecisionTree;
import com.craft_ai.interpreter.pojo.Node;

import java.util.Map;

public class DecisionTreeVisitorAdapter implements DecisionTreeVisitor {

  @Override
  public void visit(DecisionTree decisionTree) {
    visit(decisionTree.getConfiguration());

    if (decisionTree.getTrees() == null || decisionTree.getTrees().size() != 1) {
      throw new Exception("Could not handle multi decisions trees");
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
