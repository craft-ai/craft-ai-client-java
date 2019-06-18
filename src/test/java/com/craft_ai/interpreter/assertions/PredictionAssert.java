package com.craft_ai.interpreter.assertions;

import com.craft_ai.interpreter.Prediction;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class PredictionAssert extends AbstractAssert<PredictionAssert, Prediction> {

  public PredictionAssert(Prediction prediction) {
    super(prediction, PredictionAssert.class);
  }

  public static PredictionAssert assertThat(Prediction prediction) {
    return new PredictionAssert(prediction);
  }

  public PredictionAssert hasConfidence(double confidence) {
    isNotNull();

    if (!Objects.equals(actual.getConfidence(), confidence)) {
      failWithMessage("Expected confidence to be <%s> but was <%s>", confidence, actual.getConfidence());
    }

    return this;
  }

  public PredictionAssert hasPredictedValue(Object predictedValue) {
    isNotNull();

    if (!Objects.equals(actual.getPredictedValue(), predictedValue)) {
      failWithMessage("Expected predictValue to be <%s> but was <%s>", predictedValue, actual.getPredictedValue());
    }

    return this;
  }
}
