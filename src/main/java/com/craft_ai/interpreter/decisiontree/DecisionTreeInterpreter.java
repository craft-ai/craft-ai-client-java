package com.craft_ai.interpreter.decisiontree;

import com.craft_ai.interpreter.Validators;
import com.craft_ai.interpreter.decisiontree.visitor.InterpreterVisitor;
import com.craft_ai.interpreter.exceptions.Exception;
import com.craft_ai.interpreter.pojo.DecisionTree;
import com.craft_ai.interpreter.pojo.Prediction;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class DecisionTreeInterpreter {

  public static Prediction interpret(DecisionTree decisionTree, Map<String, ?> context) throws Exception {
    Set<String> inputProperties = decisionTree.getConfiguration().getContext().keySet();
    inputProperties.removeAll(Arrays.asList(decisionTree.getConfiguration().getOutput()));

    for (String property : inputProperties) {
      if (!context.containsKey(property)) {
        throw new Exception(String.format("Required property '%s' is not defined in the given context", property));
      }

      Object value = context.get(property);
      String type = decisionTree.getConfiguration().getContext().get(property).getType();

      Validators.validateValue(value, type);
    }

    InterpreterVisitor visitor = new InterpreterVisitor(context);
    decisionTree.accept(visitor);

    Prediction prediction = visitor.getPrediction();

    return prediction;
  }
}
