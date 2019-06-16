package com.craft_ai.interpreter;

public class Interval {
  private double lowerBound;
  private double upperBound;

  public Interval(double lowerBound, double upperBound) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  public double getLowerBound() {
    return lowerBound;
  }

  public double getUpperBound() {
    return upperBound;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Interval) {
      Interval castedOther = (Interval) other;
      return lowerBound == castedOther.lowerBound && upperBound == castedOther.upperBound;
    }
    return false;
  }
}
