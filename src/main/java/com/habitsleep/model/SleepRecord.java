package com.habitsleep.model;

import java.time.LocalDate;

/**
 * Represents a single sleep record with date and duration.
 */
public class SleepRecord {

  private LocalDate date;
  private double hours;
  private String quality; // "poor", "fair", "good", "excellent"

  /**
   * Constructs a SleepRecord with date, hours, and quality.
   *
   * @param date the date of the sleep
   * @param hours the number of hours slept
   * @param quality the subjective quality of sleep
   */
  public SleepRecord(LocalDate date, double hours, String quality) {
    if (hours < 0 || hours > 24) {
      throw new IllegalArgumentException("Hours must be between 0 and 24");
    }
    if (!isValidQuality(quality)) {
      throw new IllegalArgumentException("Invalid quality value");
    }
    this.date = date;
    this.hours = hours;
    this.quality = quality;
  }

  private boolean isValidQuality(String quality) {
    return quality != null
        && (quality.equals("poor")
            || quality.equals("fair")
            || quality.equals("good")
            || quality.equals("excellent"));
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public double getHours() {
    return hours;
  }

  public void setHours(double hours) {
    if (hours < 0 || hours > 24) {
      throw new IllegalArgumentException("Hours must be between 0 and 24");
    }
    this.hours = hours;
  }

  public String getQuality() {
    return quality;
  }

  public void setQuality(String quality) {
    if (!isValidQuality(quality)) {
      throw new IllegalArgumentException("Invalid quality value");
    }
    this.quality = quality;
  }

  @Override
  public String toString() {
    return String.format(
        "SleepRecord{date=%s, hours=%.1f, quality='%s'}", date, hours, quality);
  }
}
