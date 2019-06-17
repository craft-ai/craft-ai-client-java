package com.craft_ai.interpreter;

import com.craft_ai.interpreter.visitor.InterpreterVisitor;
import com.craft_ai.exceptions.CraftAiInvalidContextException;
import com.craft_ai.interpreter.Prediction;
import com.craft_ai.interpreter.PropertyType;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class DecisionTreeInterpreter {
  public static Prediction interpret(DecisionTree decisionTree, Map<String, ?> context)
      throws CraftAiInvalidContextException {
    Set<String> inputProperties = decisionTree.getConfiguration().getContext().keySet();
    inputProperties.removeAll(Arrays.asList(decisionTree.getConfiguration().getOutput()));

    for (String property : inputProperties) {
      if (!context.containsKey(property)) {
        throw new CraftAiInvalidContextException(
            String.format("Required property '%s' is not defined in the given context", property));
      }

      Object value = context.get(property);
      PropertyType type = decisionTree.getConfiguration().getContext().get(property).getType();

      type.validate(value);
    }

    InterpreterVisitor visitor = new InterpreterVisitor(context);
    decisionTree.accept(visitor);

    Prediction prediction = visitor.getPrediction();

    return prediction;
  }
}
