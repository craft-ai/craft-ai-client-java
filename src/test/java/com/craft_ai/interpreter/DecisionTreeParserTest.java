package com.craft_ai.interpreter;

import com.craft_ai.interpreter.DecisionTreeParser;
import com.craft_ai.interpreter.tools.Resources;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DecisionTreeParserTest {
  private static final String[] GOOD_TREES = { "tree_1.json", "tree_2.json", "tree_3.json" };

  @Test
  public void parse_GoodTrees_ShouldWork() throws Exception {
    for (String treeFile : GOOD_TREES) {
      String treeFileData = Resources.getResource(treeFile);
      DecisionTree decisionTree = DecisionTreeParser.parse(treeFileData);

      assertThat(decisionTree).isNotNull();
    }
  }
}
