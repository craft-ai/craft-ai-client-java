package com.craft_ai.interpreter;

import static org.junit.Assert.assertEquals;

import java.util.List;
import com.craft_ai.interpreter.tools.Resources;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTestSuiteTest {
  @Test
  public void parse_v1_trees_should_work() throws Exception {
    List<String> expectationsfiles = Resources.listResources("interpreter-test-suite/decide/trees/v1/");
    List<String> treefiles = Resources.listResources("interpreter-test-suite/decide/trees/v1/");
    assertEquals(expectationsfiles.size(), treefiles.size());

    // Removing the trees having a bad format
    treefiles.remove("interpreter-test-suite/decide/trees/v1/emptyArray.json");
    treefiles.remove("interpreter-test-suite/decide/trees/v1/emptyObject.json");

    for (String treeFile : treefiles) {
      DecisionTree tree = DecisionTreeParser.parse(Resources.getResource(treeFile));
      assertThat(tree).isNotNull();
    }
  }
}
