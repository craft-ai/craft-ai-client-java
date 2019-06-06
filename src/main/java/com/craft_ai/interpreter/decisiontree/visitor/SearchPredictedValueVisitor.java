package com.craft_ai.interpreter.decisiontree.visitor;

import com.craft_ai.interpreter.pojo.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPredictedValueVisitor extends DecisionTreeVisitorAdapter {

  private Object predictedValue;
  private List<Node> nodes;

  public SearchPredictedValueVisitor(Object predictedValue) {
    this.predictedValue = predictedValue;
    nodes = new ArrayList<>();
  }

  public List<Node> getNodes() {
    return Collections.unmodifiableList(nodes);
  }

  @Override
  public void visit(Node node) {

    if (node.isLeaf()) {
      if (node.getPredictedValue().equals(predictedValue)) {
        nodes.add(node);
      }
    } else {
      for (Node child : node.getChildren()) {
        child.accept(this);
      }
    }
  }
}
