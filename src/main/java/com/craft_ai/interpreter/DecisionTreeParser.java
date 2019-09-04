package com.craft_ai.interpreter;

import com.craft_ai.exceptions.CraftAiInvalidDecisionTreeException;
import com.fasterxml.jackson.core.JsonProcessingException;
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

  private static void validateDecisionTree(DecisionTree dt) throws CraftAiInvalidDecisionTreeException {
    if (dt.getVersion() == null) {
      throw new CraftAiInvalidDecisionTreeException(
          "Invalid decision tree format, unable to find the version informations.");
    }
    if (!dt.getVersion().matches("[1-9].[0-9].[0-9]")) {
      throw new CraftAiInvalidDecisionTreeException(
          String.format("Invalid decision tree format, \"%s\" is not a valid version.", dt.getVersion()));
    }
  }

  public static DecisionTree parse(InputStream src) throws CraftAiInvalidDecisionTreeException, IOException {
    try {
      DecisionTree dt = OBJECT_MAPPER.readValue(src, DecisionTree.class);
      validateDecisionTree(dt);
      return dt;
    } catch (JsonProcessingException e) {
      throw new CraftAiInvalidDecisionTreeException(e);
    }
  }

  public static DecisionTree parse(String src) throws CraftAiInvalidDecisionTreeException, IOException {
    try {
      DecisionTree dt = OBJECT_MAPPER.readValue(src, DecisionTree.class);
      validateDecisionTree(dt);
      return dt;
    } catch (JsonProcessingException e) {
      throw new CraftAiInvalidDecisionTreeException(e);
    }
  }

  public static DecisionTree parse(File src) throws CraftAiInvalidDecisionTreeException, IOException {
    try {
      DecisionTree dt = OBJECT_MAPPER.readValue(src, DecisionTree.class);
      validateDecisionTree(dt);
      return dt;
    } catch (JsonProcessingException e) {
      throw new CraftAiInvalidDecisionTreeException(e);
    }
  }
}
