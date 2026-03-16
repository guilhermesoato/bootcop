package com.habitsleep.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.habitsleep.model.HabitEntry;
import com.habitsleep.model.HabitTracker;
import com.habitsleep.model.SleepRecord;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Utility class for persisting and loading HabitTracker data to/from JSON files.
 */
public class DataPersistence {

  private static final String DATA_DIR = "habit_tracker_data";
  private static final String SLEEP_FILE = "sleep_records.json";
  private static final String HABITS_FILE = "habit_entries.json";

  private static final Gson gson =
      new GsonBuilder().setPrettyPrinting().create();

  static {
    new File(DATA_DIR).mkdirs();
  }

  /**
   * Saves the current state of HabitTracker to JSON files.
   *
   * @param tracker the HabitTracker to save
   * @throws IOException if an I/O error occurs
   */
  public static void saveTracker(HabitTracker tracker) throws IOException {
    saveSleepRecords(tracker.getSleepRecords());
    saveHabitEntries(tracker.getHabitEntries());
  }

  /**
   * Loads HabitTracker data from JSON files.
   *
   * @return a HabitTracker loaded from saved data
   * @throws IOException if an I/O error occurs
   */
  public static HabitTracker loadTracker() throws IOException {
    HabitTracker tracker = new HabitTracker();

    SleepRecord[] sleepRecords = loadSleepRecords();
    for (SleepRecord record : sleepRecords) {
      tracker.addSleepRecord(record);
    }

    HabitEntry[] habitEntries = loadHabitEntries();
    for (HabitEntry entry : habitEntries) {
      tracker.addHabitEntry(entry);
    }

    return tracker;
  }

  private static void saveSleepRecords(java.util.List<SleepRecord> records)
      throws IOException {
    File file = new File(DATA_DIR, SLEEP_FILE);
    try (FileWriter writer = new FileWriter(file)) {
      gson.toJson(records, writer);
    }
  }

  private static SleepRecord[] loadSleepRecords() throws IOException {
    File file = new File(DATA_DIR, SLEEP_FILE);
    if (!file.exists()) {
      return new SleepRecord[0];
    }
    try (FileReader reader = new FileReader(file)) {
      SleepRecord[] records = gson.fromJson(reader, SleepRecord[].class);
      return records != null ? records : new SleepRecord[0];
    }
  }

  private static void saveHabitEntries(java.util.List<HabitEntry> entries)
      throws IOException {
    File file = new File(DATA_DIR, HABITS_FILE);
    try (FileWriter writer = new FileWriter(file)) {
      gson.toJson(entries, writer);
    }
  }

  private static HabitEntry[] loadHabitEntries() throws IOException {
    File file = new File(DATA_DIR, HABITS_FILE);
    if (!file.exists()) {
      return new HabitEntry[0];
    }
    try (FileReader reader = new FileReader(file)) {
      HabitEntry[] entries = gson.fromJson(reader, HabitEntry[].class);
      return entries != null ? entries : new HabitEntry[0];
    }
  }
}
