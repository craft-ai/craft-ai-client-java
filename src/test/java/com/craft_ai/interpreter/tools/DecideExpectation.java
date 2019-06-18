package com.craft_ai.interpreter.tools;

import java.util.Map;

import com.craft_ai.interpreter.DecideOutput;

public class DecideExpectation {
  public String title;
  public String serializedDecisionTree;
  public Map<String, ?> context;
  public Map<String, ?> error;
  public DecideOutput output;
}
