package com.craft_ai.interpreter.operators;

import com.craft_ai.interpreter.exceptions.Exception;

public class Operators {

  public static final String GTE = ">=";
  public static final String IN = "[in[";
  public static final String IS = "is";
  public static final String LT = "<";

  public static boolean lessOperator(Object property, Object operand) {
    if (isNumberType(property)) {
      return Float.valueOf(property.toString()) < Float.valueOf(operand.toString());
    }
    return false;
  }

  public static boolean intervalOperator(Object property, Object o1, Object o2) {
    if (o1 == null || o2 == null) {
      throw new Exception("Error interval boundary NULL ");
    }

    float operand1 = Float.valueOf(o1.toString());
    float operand2 = Float.valueOf(o2.toString());
    float fProperty = Float.valueOf(property.toString());

    if (operand1 < operand2) {
      return (fProperty >= operand1 && fProperty < operand2);
    } else {
      return (fProperty >= operand1 || fProperty < operand2);
    }
  }

  public static boolean equalOperator(Object property, Object operand) {
    if (isNumberType(property)) {
      return Float.valueOf(property.toString()).equals(Float.valueOf(operand.toString()));

    } else {
      return ("" + property).equalsIgnoreCase("" + operand);

    }
  }

  public static boolean greaterOperator(Object property, Object operand) {
    if (isNumberType(property)) {
      return Float.valueOf(property.toString()) >= Float.valueOf(operand.toString());

    }
    return false;
  }

  public static boolean isNumberType(Object obj) {
    return obj instanceof Number;
  }
}
