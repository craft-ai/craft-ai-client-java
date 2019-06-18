package com.craft_ai.exceptions;

import com.craft_ai.interpreter.PropertyType;

public class CraftAiInvalidValueException extends CraftAiInvalidContextException {
  private static final long serialVersionUID = -1685125293418563257L;

  public CraftAiInvalidValueException(Object value, PropertyType type) {
    super(String.format("'%s' is not a valid value of type '%s'.", value, type));
  }

  public CraftAiInvalidValueException(String property, Object value, PropertyType type) {
    super(String.format("'%s' is not a valid value for property '%s' of type '%s'.", value, property, type));
  }
}
