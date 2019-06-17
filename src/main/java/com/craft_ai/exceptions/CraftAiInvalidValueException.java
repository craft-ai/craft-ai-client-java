package com.craft_ai.exceptions;

import com.craft_ai.interpreter.PropertyType;

public class CraftAiInvalidValueException extends CraftAiInvalidContextException {
  private static final long serialVersionUID = -1685125293418563257L;

  public CraftAiInvalidValueException(Object value, PropertyType type) {
    super(String.format("'%s' is an invalid value for type '%s', expected values are %s.", value, type,
        type.getExpectedValues()));
  }
}
