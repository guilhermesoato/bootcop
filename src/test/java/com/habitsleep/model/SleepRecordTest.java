package com.habitsleep.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SleepRecordTest {

  @Test
  void testSleepRecordCreation() {
    LocalDate date = LocalDate.now();
    SleepRecord record = new SleepRecord(date, 8.0, "good");

    assertEquals(date, record.getDate());
    assertEquals(8.0, record.getHours());
    assertEquals("good", record.getQuality());
  }

  @Test
  void testSleepRecordWithInvalidHours() {
    LocalDate date = LocalDate.now();

    assertThrows(
        IllegalArgumentException.class,
        () -> new SleepRecord(date, -1, "good"),
        "Should throw for negative hours");

    assertThrows(
        IllegalArgumentException.class,
        () -> new SleepRecord(date, 25, "good"),
        "Should throw for hours > 24");
  }

  @Test
  void testSleepRecordWithInvalidQuality() {
    LocalDate date = LocalDate.now();

    assertThrows(
        IllegalArgumentException.class,
        () -> new SleepRecord(date, 8.0, "invalid"),
        "Should throw for invalid quality");

    assertThrows(
        IllegalArgumentException.class,
        () -> new SleepRecord(date, 8.0, null),
        "Should throw for null quality");
  }

  @Test
  void testSetHours() {
    LocalDate date = LocalDate.now();
    SleepRecord record = new SleepRecord(date, 8.0, "good");

    record.setHours(7.5);
    assertEquals(7.5, record.getHours());

    assertThrows(IllegalArgumentException.class, () -> record.setHours(30));
  }

  @Test
  void testSetQuality() {
    LocalDate date = LocalDate.now();
    SleepRecord record = new SleepRecord(date, 8.0, "good");

    record.setQuality("excellent");
    assertEquals("excellent", record.getQuality());

    assertThrows(IllegalArgumentException.class, () -> record.setQuality("bad"));
  }

  @Test
  void testAllValidQualityValues() {
    LocalDate date = LocalDate.now();
    String[] validQualities = {"poor", "fair", "good", "excellent"};

    for (String quality : validQualities) {
      SleepRecord record = new SleepRecord(date, 8.0, quality);
      assertEquals(quality, record.getQuality());
    }
  }
}
