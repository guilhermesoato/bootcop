package com.habitsleep;

import com.habitsleep.model.HabitTracker;
import com.habitsleep.ui.SleepTrackerUI;
import com.habitsleep.util.DataPersistence;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for the Sleep Tracker application.
 */
public class SleepTrackerApp extends Application {

  private HabitTracker tracker;

  @Override
  public void start(Stage primaryStage) {
    try {
      // Load or create tracker
      tracker = loadTracker();

      // Create UI
      SleepTrackerUI ui = new SleepTrackerUI(tracker, this::saveTracker);
      Scene scene = new Scene(ui.getRoot(), 900, 700);

      // Configure stage
      primaryStage.setTitle("Sleep Tracker - Daily Habit Monitor");
      primaryStage.setScene(scene);
      primaryStage.setOnCloseRequest(event -> saveTracker());
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private HabitTracker loadTracker() {
    try {
      return DataPersistence.loadTracker();
    } catch (Exception e) {
      System.err.println("Could not load existing data. Starting fresh.");
      return new HabitTracker();
    }
  }

  public void saveTracker() {
    try {
      DataPersistence.saveTracker(tracker);
      System.out.println("Data saved successfully");
    } catch (Exception e) {
      System.err.println("Error saving data: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
