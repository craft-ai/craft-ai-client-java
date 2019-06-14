package com.craft_ai.exceptions;

public class CraftAiUnknownOperatorException extends RuntimeException {
  private static final long serialVersionUID = 6417498875868649367L;

  public CraftAiUnknownOperatorException(String operator) {
    super(String.format("'%s' is an unknown operator.", operator));
  }
}
