package com.craft_ai.exceptions;

public class CraftAiUnknownPropertyTypeException extends CraftAiInvalidDecisionTreeException {
  private static final long serialVersionUID = 6417498875868649367L;

  public CraftAiUnknownPropertyTypeException(String propertyType) {
    super(String.format("'%s' is an unknown property type.", propertyType));
  }
}
