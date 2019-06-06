package com.craft_ai.interpreter;

public enum PropertyType {

  TIME_OF_DAY("time_of_day"), DAY_OF_MONTH("day_of_month"), DAY("day_of_week"), MONTH_OF_YEAR("month_of_year"),
  TIMEZONE("timezone"), CONTINOUS("continuous"), ENUMERATION("enum");

  private String type;

  PropertyType(String type) {
    this.type = type;
  }

  public String toString() {
    return this.type;
  }
}
