package com.craft_ai.interpreter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.craft_ai.interpreter.tools.Resources;
import com.craft_ai.interpreter.visitor.SearchPredictedValueVisitor;

import org.junit.jupiter.api.Test;

public class SearchPredictedValueVisitorTest {
  @Test
  public void accept_WithANonExistingPredictedValue_ReturnsNoNodes() throws java.lang.Exception {
    DecisionTree decisionTree = DecisionTreeParser.parse(Resources.getResource("tree_1.json"));
    SearchPredictedValueVisitor visitor = new SearchPredictedValueVisitor("True");
    decisionTree.accept(visitor);

    List<Node> nodes = visitor.getNodes();

    assertThat(nodes).hasSize(0);
  }

  @Test
  public void accept_WithAnExistingPredictedValue_ReturnsTheExpectedNodes() throws java.lang.Exception {
    DecisionTree decisionTree = DecisionTreeParser.parse(Resources.getResource("tree_1.json"));
    SearchPredictedValueVisitor visitor = new SearchPredictedValueVisitor("OFF");
    decisionTree.accept(visitor);

    List<Node> nodes = visitor.getNodes();

    assertThat(nodes).hasSize(9);
  }
}
