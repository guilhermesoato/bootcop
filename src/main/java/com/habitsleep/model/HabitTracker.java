package com.habitsleep.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main business logic for tracking sleep and daily habits.
 */
public class HabitTracker {

  private final List<SleepRecord> sleepRecords;
  private final List<HabitEntry> habitEntries;

  /** Constructs an empty HabitTracker. */
  public HabitTracker() {
    this.sleepRecords = new ArrayList<>();
    this.habitEntries = new ArrayList<>();
  }

  /**
   * Adds a sleep record.
   *
   * @param record the sleep record to add
   */
  public void addSleepRecord(SleepRecord record) {
    if (record == null) {
      throw new IllegalArgumentException("Sleep record cannot be null");
    }
    sleepRecords.add(record);
  }

  /**
   * Removes a sleep record by date.
   *
   * @param date the date of the record to remove
   * @return true if a record was removed, false otherwise
   */
  public boolean removeSleepRecord(LocalDate date) {
    return sleepRecords.removeIf(record -> record.getDate().equals(date));
  }

  /**
   * Gets all sleep records.
   *
   * @return a list of all sleep records
   */
  public List<SleepRecord> getSleepRecords() {
    return new ArrayList<>(sleepRecords);
  }

  /**
   * Gets the average sleep hours for the last N days.
   *
   * @param days the number of days to consider
   * @return the average sleep hours
   */
  public double getAverageSleepHours(int days) {
    if (days <= 0) {
      throw new IllegalArgumentException("Days must be positive");
    }
    LocalDate startDate = LocalDate.now().minusDays(days);

    double totalHours =
        sleepRecords.stream()
            .filter(record -> !record.getDate().isBefore(startDate))
            .mapToDouble(SleepRecord::getHours)
            .sum();

    long recordCount =
        sleepRecords.stream().filter(record -> !record.getDate().isBefore(startDate)).count();

    return recordCount == 0 ? 0 : totalHours / recordCount;
  }

  /**
   * Adds a habit entry.
   *
   * @param entry the habit entry to add
   */
  public void addHabitEntry(HabitEntry entry) {
    if (entry == null) {
      throw new IllegalArgumentException("Habit entry cannot be null");
    }
    habitEntries.add(entry);
  }

  /**
   * Removes a habit entry by date and habit name.
   *
   * @param date the date of the entry
   * @param habitName the name of the habit
   * @return true if an entry was removed, false otherwise
   */
  public boolean removeHabitEntry(LocalDate date, String habitName) {
    return habitEntries.removeIf(
        entry -> entry.getDate().equals(date) && entry.getHabitName().equals(habitName));
  }

  /**
   * Gets all habit entries.
   *
   * @return a list of all habit entries
   */
  public List<HabitEntry> getHabitEntries() {
    return new ArrayList<>(habitEntries);
  }

  /**
   * Gets habit entries for a specific date.
   *
   * @param date the target date
   * @return a list of habit entries for that date
   */
  public List<HabitEntry> getHabitEntriesByDate(LocalDate date) {
    return habitEntries.stream()
        .filter(entry -> entry.getDate().equals(date))
        .collect(Collectors.toList());
  }

  /**
   * Gets the completion rate for a habit over the last N days.
   *
   * @param habitName the name of the habit
   * @param days the number of days to consider
   * @return the completion percentage (0-100)
   */
  public double getHabitCompletionRate(String habitName, int days) {
    if (days <= 0) {
      throw new IllegalArgumentException("Days must be positive");
    }
    LocalDate startDate = LocalDate.now().minusDays(days);

    List<HabitEntry> recentEntries =
        habitEntries.stream()
            .filter(
                entry ->
                    entry.getHabitName().equals(habitName)
                        && !entry.getDate().isBefore(startDate))
            .collect(Collectors.toList());

    if (recentEntries.isEmpty()) {
      return 0;
    }

    long completedCount = recentEntries.stream().filter(HabitEntry::isCompleted).count();
    return (completedCount * 100.0) / recentEntries.size();
  }
}
