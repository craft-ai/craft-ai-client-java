package com.craft_ai.interpreter;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecideOutput {

  @JsonProperty("_version")
  public String version;
  public Map<String, ?> context;
  @JsonProperty("output")
  public Map<String, Prediction> predictions;

  public DecideOutput() {
    this.context = new HashMap<String, Object>();
    this.predictions = new HashMap<String, Prediction>();
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Map<String, ?> getContext() {
    return context;
  }

  public void setContext(Map<String, ?> context) {
    this.context = context;
  }

  public Map<String, Prediction> getPredictions() {
    return predictions;
  }

  public void setPredictions(Map<String, Prediction> predictions) {
    this.predictions = predictions;
  }

  public Prediction getPrediction(String output) {
    return predictions.get(output);
  }

  public Prediction setPrediction(String output, Prediction prediction) {
    return predictions.put(output, prediction);
  }
}
