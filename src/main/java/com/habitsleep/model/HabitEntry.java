package com.habitsleep.model;

import java.time.LocalDate;

/**
 * Represents a daily habit entry (exercise, meditation, hydration, etc.).
 */
public class HabitEntry {

  private LocalDate date;
  private String habitName;
  private boolean completed;
  private String notes;

  /**
   * Constructs a HabitEntry.
   *
   * @param date the date of the habit
   * @param habitName the name of the habit
   * @param completed whether the habit was completed
   * @param notes optional notes about the habit
   */
  public HabitEntry(LocalDate date, String habitName, boolean completed, String notes) {
    if (habitName == null || habitName.trim().isEmpty()) {
      throw new IllegalArgumentException("Habit name cannot be empty");
    }
    this.date = date;
    this.habitName = habitName;
    this.completed = completed;
    this.notes = notes;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getHabitName() {
    return habitName;
  }

  public void setHabitName(String habitName) {
    if (habitName == null || habitName.trim().isEmpty()) {
      throw new IllegalArgumentException("Habit name cannot be empty");
    }
    this.habitName = habitName;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return String.format(
        "HabitEntry{date=%s, habit='%s', completed=%s, notes='%s'}",
        date, habitName, completed, notes);
  }
}
