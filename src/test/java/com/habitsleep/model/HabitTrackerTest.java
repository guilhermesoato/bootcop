package com.habitsleep.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HabitTrackerTest {

  private HabitTracker tracker;
  private LocalDate today;

  @BeforeEach
  void setUp() {
    tracker = new HabitTracker();
    today = LocalDate.now();
  }

  @Test
  void testAddSleepRecord() {
    SleepRecord record = new SleepRecord(today, 8.0, "good");
    tracker.addSleepRecord(record);

    assertEquals(1, tracker.getSleepRecords().size());
    assertEquals(record, tracker.getSleepRecords().get(0));
  }

  @Test
  void testAddNullSleepRecord() {
    assertThrows(
        IllegalArgumentException.class,
        () -> tracker.addSleepRecord(null),
        "Should throw for null sleep record");
  }

  @Test
  void testRemoveSleepRecord() {
    SleepRecord record = new SleepRecord(today, 8.0, "good");
    tracker.addSleepRecord(record);

    assertTrue(tracker.removeSleepRecord(today));
    assertEquals(0, tracker.getSleepRecords().size());
  }

  @Test
  void testRemoveSleepRecordNotFound() {
    assertFalse(tracker.removeSleepRecord(today), "Should return false when record not found");
  }

  @Test
  void testGetAverageSleepHours() {
    tracker.addSleepRecord(new SleepRecord(today, 8.0, "good"));
    tracker.addSleepRecord(new SleepRecord(today.minusDays(1), 7.0, "fair"));
    tracker.addSleepRecord(new SleepRecord(today.minusDays(2), 9.0, "excellent"));

    double average = tracker.getAverageSleepHours(7);
    assertEquals(8.0, average, 0.01, "Average should be 8.0");
  }

  @Test
  void testGetAverageSleepHoursWithNoRecords() {
    double average = tracker.getAverageSleepHours(7);
    assertEquals(0, average, "Should return 0 when no records exist");
  }

  @Test
  void testGetAverageSleepHoursWithInvalidDays() {
    assertThrows(
        IllegalArgumentException.class,
        () -> tracker.getAverageSleepHours(0),
        "Should throw for 0 days");

    assertThrows(
        IllegalArgumentException.class,
        () -> tracker.getAverageSleepHours(-5),
        "Should throw for negative days");
  }

  @Test
  void testAddHabitEntry() {
    HabitEntry entry = new HabitEntry(today, "Exercise", true, "5km run");
    tracker.addHabitEntry(entry);

    assertEquals(1, tracker.getHabitEntries().size());
    assertEquals(entry, tracker.getHabitEntries().get(0));
  }

  @Test
  void testAddNullHabitEntry() {
    assertThrows(
        IllegalArgumentException.class,
        () -> tracker.addHabitEntry(null),
        "Should throw for null habit entry");
  }

  @Test
  void testRemoveHabitEntry() {
    HabitEntry entry = new HabitEntry(today, "Exercise", true, "notes");
    tracker.addHabitEntry(entry);

    assertTrue(tracker.removeHabitEntry(today, "Exercise"));
    assertEquals(0, tracker.getHabitEntries().size());
  }

  @Test
  void testRemoveHabitEntryNotFound() {
    assertFalse(tracker.removeHabitEntry(today, "NonExistent"));
  }

  @Test
  void testGetHabitEntriesByDate() {
    tracker.addHabitEntry(new HabitEntry(today, "Exercise", true, ""));
    tracker.addHabitEntry(new HabitEntry(today, "Meditation", false, ""));
    tracker.addHabitEntry(new HabitEntry(today.minusDays(1), "Exercise", true, ""));

    assertEquals(2, tracker.getHabitEntriesByDate(today).size());
    assertEquals(1, tracker.getHabitEntriesByDate(today.minusDays(1)).size());
  }

  @Test
  void testGetHabitCompletionRate() {
    // Add 5 exercise entries: 3 completed, 2 not completed
    tracker.addHabitEntry(new HabitEntry(today, "Exercise", true, ""));
    tracker.addHabitEntry(new HabitEntry(today.minusDays(1), "Exercise", true, ""));
    tracker.addHabitEntry(new HabitEntry(today.minusDays(2), "Exercise", true, ""));
    tracker.addHabitEntry(new HabitEntry(today.minusDays(3), "Exercise", false, ""));
    tracker.addHabitEntry(new HabitEntry(today.minusDays(4), "Exercise", false, ""));

    double rate = tracker.getHabitCompletionRate("Exercise", 7);
    assertEquals(60.0, rate, 0.01, "Completion rate should be 60%");
  }

  @Test
  void testGetHabitCompletionRateNoEntries() {
    double rate = tracker.getHabitCompletionRate("NonExistent", 7);
    assertEquals(0, rate, "Should return 0 when no entries exist");
  }

  @Test
  void testGetHabitCompletionRateWithInvalidDays() {
    assertThrows(
        IllegalArgumentException.class,
        () -> tracker.getHabitCompletionRate("Exercise", 0),
        "Should throw for 0 days");
  }

  @Test
  void testGetSleepRecordsReturnsIndependentList() {
    tracker.addSleepRecord(new SleepRecord(today, 8.0, "good"));
    var list1 = tracker.getSleepRecords();
    list1.clear();

    assertEquals(1, tracker.getSleepRecords().size(), "Original list should not be affected");
  }

  @Test
  void testMultipleSleepRecordsSameDateOverwrite() {
    tracker.addSleepRecord(new SleepRecord(today, 8.0, "good"));
    tracker.addSleepRecord(new SleepRecord(today, 9.0, "excellent"));

    assertEquals(2, tracker.getSleepRecords().size(), "Both records should exist");
  }
}
