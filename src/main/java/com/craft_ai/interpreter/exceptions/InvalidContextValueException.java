package com.craft_ai.interpreter.exceptions;

import com.craft_ai.interpreter.PropertyType;

public class InvalidContextValueException extends Exception {
  private static final long serialVersionUID = -1685125293418563257L;

  public InvalidContextValueException(Object value, PropertyType type) {
    super(String.format("'%s' is an invalid value for type '%s'.", value, type));
  }

  public InvalidContextValueException(Object value, PropertyType type, String expectedValues) {
    super(
        String.format("'%s' is an invalid value for type '%s', expected values are %s.", value, type, expectedValues));
  }
}
