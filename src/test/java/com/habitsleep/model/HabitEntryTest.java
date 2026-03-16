package com.habitsleep.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class HabitEntryTest {

  @Test
  void testHabitEntryCreation() {
    LocalDate date = LocalDate.now();
    HabitEntry entry = new HabitEntry(date, "Exercise", true, "Went for a 5km run");

    assertEquals(date, entry.getDate());
    assertEquals("Exercise", entry.getHabitName());
    assertTrue(entry.isCompleted());
    assertEquals("Went for a 5km run", entry.getNotes());
  }

  @Test
  void testHabitEntryWithEmptyHabitName() {
    LocalDate date = LocalDate.now();

    assertThrows(
        IllegalArgumentException.class,
        () -> new HabitEntry(date, "", true, "notes"),
        "Should throw for empty habit name");

    assertThrows(
        IllegalArgumentException.class,
        () -> new HabitEntry(date, null, true, "notes"),
        "Should throw for null habit name");

    assertThrows(
        IllegalArgumentException.class,
        () -> new HabitEntry(date, "   ", true, "notes"),
        "Should throw for whitespace-only habit name");
  }

  @Test
  void testSetHabitName() {
    LocalDate date = LocalDate.now();
    HabitEntry entry = new HabitEntry(date, "Exercise", true, "notes");

    entry.setHabitName("Meditation");
    assertEquals("Meditation", entry.getHabitName());

    assertThrows(IllegalArgumentException.class, () -> entry.setHabitName(""));
  }

  @Test
  void testSetCompleted() {
    LocalDate date = LocalDate.now();
    HabitEntry entry = new HabitEntry(date, "Exercise", true, "notes");

    assertTrue(entry.isCompleted());
    entry.setCompleted(false);
    assertFalse(entry.isCompleted());
  }

  @Test
  void testSetNotes() {
    LocalDate date = LocalDate.now();
    HabitEntry entry = new HabitEntry(date, "Exercise", true, "initial notes");

    entry.setNotes("Updated notes");
    assertEquals("Updated notes", entry.getNotes());

    entry.setNotes(null);
    assertNull(entry.getNotes());
  }
}
