package com.craft_ai.interpreter;

import com.craft_ai.interpreter.decisiontree.DecisionTreeInterpreter;
import com.craft_ai.interpreter.decisiontree.DecisionTreeParser;
import com.craft_ai.exceptions.CraftAiInvalidContextException;
import com.craft_ai.interpreter.pojo.DecisionTree;
import com.craft_ai.interpreter.pojo.Prediction;
import com.craft_ai.interpreter.tools.Resources;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.craft_ai.interpreter.assertions.DecisionRuleAssert.assertThat;
import static com.craft_ai.interpreter.assertions.PredictionAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DecisionTreesInterpreterTest {

  @Test
  public void interpret_validContext_ShouldWork() throws java.lang.Exception {

    Map<String, Object> context = new HashMap<>();
    context.put("day_of_week", 5);
    context.put("time", 18);
    context.put("timezone", "CEST");

    DecisionTree decisionTree = DecisionTreeParser.parse(Resources.getResource("tree_2.json"));
    Prediction prediction = DecisionTreeInterpreter.interpret(decisionTree, context);

    assertThat(prediction).hasConfidence(0.8053388595581055d).hasPredictValue("False");

    assertThat(prediction.getDecisionRule()).hasProperty("time").hasOperator("[in[").hasOperand(0, 18.283333);
  }

  @Test
  public void interpret_contextWithMissingProperty_ShouldThrowTheRightError() throws IOException {
    DecisionTree decisionTree = DecisionTreeParser.parse(Resources.getResource("tree_1.json"));

    Map<String, Object> context = new HashMap<>();
    context.put("continuous1", 0);
    context.put("continuous2", 0);
    context.put("continuous3", 167);
    context.put("continuous4", 21);
    context.put("enum1", "toto");
    context.put("enum2", "ko");
    context.put("continuous5", 0);
    context.put("enum3", "yes");
    context.put("continuous6", 1000000);
    context.put("enum4", "yes");
    context.put("enum5", "good");
    context.put("enum6", "non_applicable");
    context.put("enum7", "average");

    assertThatExceptionOfType(CraftAiInvalidContextException.class)
        .isThrownBy(() -> DecisionTreeInterpreter.interpret(decisionTree, context))
        .withMessage("Required property 'movement' is not defined in the given context");
  }
}
