package com.craft_ai.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CraftAiInvalidDecisionTreeException extends IllegalArgumentException {
  private static final long serialVersionUID = -6544992340830525078L;

  public CraftAiInvalidDecisionTreeException(String message) {
    super(message);
  }

  public CraftAiInvalidDecisionTreeException(JsonParseException cause) {
    super("Invalid decision tree format, the given json is not valid.");
  }

  public CraftAiInvalidDecisionTreeException(JsonProcessingException cause) {
    super("Invalid decision tree format, the given json is not an object.", cause);
  }
}
