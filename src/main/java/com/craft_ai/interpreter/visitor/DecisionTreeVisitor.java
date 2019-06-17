package com.craft_ai.interpreter.visitor;

import com.craft_ai.interpreter.Configuration;
import com.craft_ai.interpreter.DecisionTree;
import com.craft_ai.interpreter.Node;

public interface DecisionTreeVisitor {

  void visit(DecisionTree decisionTree);

  void visit(Configuration configuration);

  void visit(Node node);

}
