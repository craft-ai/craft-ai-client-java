package com.craft_ai.interpreter.decisiontree;

import com.craft_ai.interpreter.pojo.DecisionTree;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DecisionTreeParser {

  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  static {
    OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
  }

  public static DecisionTree parse(InputStream src) throws IOException {
    return OBJECT_MAPPER.readValue(src, DecisionTree.class);
  }

  public static DecisionTree parse(String src) throws IOException {
    return OBJECT_MAPPER.readValue(src, DecisionTree.class);
  }

  public static DecisionTree parse(File src) throws IOException {
    return OBJECT_MAPPER.readValue(src, DecisionTree.class);
  }

}
