package com.craft_ai.interpreter;

import static com.craft_ai.interpreter.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
          expectation.title = expectationFilePath + " - " + expectation.title;
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
        .map(expectation -> DynamicTest.dynamicTest(expectation.title, () -> {
          if (expectation.error == null) {
            assertThatCode(() -> DecisionTreeParser.parse(expectation.serializedDecisionTree))
                .doesNotThrowAnyException();
            DecisionTree tree = DecisionTreeParser.parse(expectation.serializedDecisionTree);
            assertThat(tree).isNotNull();
            assertThat(tree.getVersion()).isIn("1.0.0", "1.1.0");
            assertThatCode(() -> tree.decide(expectation.context)).doesNotThrowAnyException();
            DecideOutput output = tree.decide(expectation.context);
            assertThat(output).isNotNull().isEqualToComparingFieldByFieldRecursively(expectation.output);
          } else {
            assertThatThrownBy(() -> {
              DecisionTree tree = DecisionTreeParser.parse(expectation.serializedDecisionTree);
              tree.decide(expectation.context);
            }).hasMessage((String) expectation.error.get("message"));
          }
        }));
  }
}
