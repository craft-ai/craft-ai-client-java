package com.craft_ai.interpreter;

import java.util.Map;

public class Configuration {

  private long timeQuantum;
  private long learningPeriod;
  private long treeMaxDepth;
  private Map<String, Variable> context;
  private String[] output;

  public long getTimeQuantum() {
    return timeQuantum;
  }

  public void setTimeQuantum(long timeQuantum) {
    this.timeQuantum = timeQuantum;
  }

  public long getLearningPeriod() {
    return learningPeriod;
  }

  public void setLearningPeriod(long learningPeriod) {
    this.learningPeriod = learningPeriod;
  }

  public long getTreeMaxDepth() {
    return treeMaxDepth;
  }

  public void setTreeMaxDepth(long treeMaxDepth) {
    this.treeMaxDepth = treeMaxDepth;
  }

  public Map<String, Variable> getContext() {
    return context;
  }

  public void setContext(Map<String, Variable> context) {
    this.context = context;
  }

  public String[] getOutput() {
    return output;
  }

  public void setOutput(String[] output) {
    this.output = output;
  }
}
