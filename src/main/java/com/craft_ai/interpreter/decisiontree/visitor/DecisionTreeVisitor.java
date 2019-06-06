package com.craft_ai.interpreter.decisiontree.visitor;

import com.craft_ai.interpreter.pojo.Configuration;
import com.craft_ai.interpreter.pojo.DecisionTree;
import com.craft_ai.interpreter.pojo.Node;

public interface DecisionTreeVisitor {

  void visit(DecisionTree decisionTree);

  void visit(Configuration configuration);

  void visit(Node node);

}
