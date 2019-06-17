package com.craft_ai.interpreter;

public class PropertyConfiguration {

  private PropertyType type;
  private boolean isGenerated;

  public PropertyType getType() {
    return type;
  }

  public void setType(PropertyType type) {
    this.type = type;
  }

  public boolean getIsGenerated() {
    return isGenerated;
  }

  public void setIsGenerated(boolean generated) {
    isGenerated = generated;
  }
}
