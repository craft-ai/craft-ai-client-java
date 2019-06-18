package com.craft_ai.interpreter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

import com.craft_ai.interpreter.tools.DecideExpectation;
import com.craft_ai.interpreter.tools.Resources;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

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

  @TestFactory
  public Stream<DynamicTest> interpreterTestSuiteV1Factory() throws IOException, URISyntaxException {
    return loadExpectations("interpreter-test-suite/decide/expectations/v1/")
        .filter(expectation -> expectation.error == null)
        .map(expectation -> DynamicTest.dynamicTest(expectation.title, () -> {
          assertThatCode(() -> DecisionTreeParser.parse(expectation.serializedDecisionTree)).doesNotThrowAnyException();
          assertThat(DecisionTreeParser.parse(expectation.serializedDecisionTree)).isNotNull();
        }));
  }
}
