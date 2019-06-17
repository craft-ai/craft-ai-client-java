package com.craft_ai.interpreter;

import com.craft_ai.interpreter.visitor.DecisionTreeVisitor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class DecisionTree {

  @JsonProperty("_version")
  private String version;
  private Map<String, Node> trees;
  private Configuration configuration;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Map<String, Node> getTrees() {
    return trees;
  }

  public void setTrees(Map<String, Node> trees) {
    this.trees = trees;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public void accept(DecisionTreeVisitor visitor) {
    visitor.visit(this);
  }

}
