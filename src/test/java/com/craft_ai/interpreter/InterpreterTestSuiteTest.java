package com.craft_ai.interpreter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.craft_ai.interpreter.tools.DecideExpectation;
import com.craft_ai.interpreter.tools.Resources;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTestSuiteTest {
  public static Stream<DecideExpectation> loadExpectations(String path) throws IOException, URISyntaxException {
    return Resources.listResources(path).stream().flatMap(expectationFilePath -> {

      ObjectMapper mapper = new ObjectMapper();
      try {
        List<DecideExpectation> expectations = mapper.readValue(Resources.getResource(expectationFilePath),
            new TypeReference<List<DecideExpectation>>() {
            });
        String serializedDecisionTree = Resources.getResource(expectationFilePath.replace("expectations", "trees"));
        return expectations.stream().map(expectation -> {
          expectation.serializedDecisionTree = serializedDecisionTree;
          return expectation;
        });
      } catch (Exception e) {
        e.printStackTrace();
        return Stream.empty();
      }
    });
  }

  @Test
  public void parse_v1_trees_should_work() throws Exception {
    List<String> okSerializedTrees = loadExpectations("interpreter-test-suite/decide/expectations/v1/")
        .filter(expectation -> expectation.error == null).map(expectation -> expectation.serializedDecisionTree)
        .collect(Collectors.toList());

    for (String serializedTree : okSerializedTrees) {
      DecisionTree tree = DecisionTreeParser.parse(serializedTree);
      assertThat(tree).isNotNull();
    }
  }
}
