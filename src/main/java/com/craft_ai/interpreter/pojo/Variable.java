package com.craft_ai.interpreter.pojo;

public class Variable {

  private String type;
  private boolean isGenerated;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean getIsGenerated() {
    return isGenerated;
  }

  public void setIsGenerated(boolean generated) {
    isGenerated = generated;
  }
}
