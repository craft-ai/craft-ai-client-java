package com.craft_ai.exceptions;

public class CraftAiInvalidContextException extends IllegalArgumentException {
  private static final long serialVersionUID = -1655975348951167296L;

  public CraftAiInvalidContextException(String message) {
    super(message);
  }

  public CraftAiInvalidContextException(String message, Exception reason) {
    super(message, reason);
  }
}
