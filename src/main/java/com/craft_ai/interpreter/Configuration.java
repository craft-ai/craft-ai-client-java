package com.craft_ai.interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.craft_ai.exceptions.CraftAiInvalidContextException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

  private long timeQuantum;
  private long learningPeriod;
  private long treeMaxDepth;
  @JsonProperty("context")
  private Map<String, PropertyConfiguration> properties;
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

  public Map<String, PropertyConfiguration> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, PropertyConfiguration> properties) {
    this.properties = properties;
  }

  public String[] getOutput() {
    return output;
  }

  public void setOutput(String[] output) {
    this.output = output;
  }

  public Map<String, ?> validate(Map<String, ?> context) throws CraftAiInvalidContextException {
    Set<String> inputProperties = properties.keySet();
    inputProperties.removeAll(Arrays.asList(output));

    Map<String, Object> validatedInputContext = new HashMap<String, Object>();

    for (String property : inputProperties) {
      if (!context.containsKey(property)) {
        throw new CraftAiInvalidContextException(
            String.format("Required property '%s' is not defined in the given context.", property));
      }

      Object value = context.get(property);
      PropertyType type = properties.get(property).getType();

      type.validate(value);

      validatedInputContext.put(property, (Object) value);
    }

    return validatedInputContext;
  }
}
