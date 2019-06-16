package com.craft_ai.interpreter;

import com.craft_ai.interpreter.decisiontree.DecisionTreeParser;
import com.craft_ai.interpreter.decisiontree.visitor.SearchPredictedValueVisitor;
import com.craft_ai.interpreter.DecisionTree;
import com.craft_ai.interpreter.Node;
import com.craft_ai.interpreter.tools.Resources;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
