package com.habitsleep.ui;

import com.habitsleep.model.HabitEntry;
import com.habitsleep.model.HabitTracker;
import com.habitsleep.model.SleepRecord;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.time.LocalDate;

/**
 * Main UI class for the Sleep Tracker application using JavaFX.
 */
public class SleepTrackerUI {

  private final HabitTracker tracker;
  private final Runnable saveCallback;
  private final BorderPane root;
  private final ListView<String> sleepListView;
  private final ListView<String> habitListView;

  /**
   * Constructs the Sleep Tracker UI.
   *
   * @param tracker the HabitTracker instance
   * @param saveCallback callback for saving data
   */
  public SleepTrackerUI(HabitTracker tracker, Runnable saveCallback) {
    this.tracker = tracker;
    this.saveCallback = saveCallback;
    this.root = new BorderPane();
    this.sleepListView = new ListView<>();
    this.habitListView = new ListView<>();

    initializeUI();
  }

  private void initializeUI() {
    root.setStyle("-fx-padding: 15px; -fx-background-color: #f5f5f5;");

    // Header
    VBox header = createHeader();
    root.setTop(header);

    // Content with tabs
    TabPane tabPane = createTabPane();
    root.setCenter(tabPane);
  }

  private VBox createHeader() {
    VBox header = new VBox(10);
    header.setStyle("-fx-padding: 10px; -fx-background-color: #2c3e50; -fx-text-fill: white;");

    Label title = new Label("🌙 Sleep Tracker - Daily Habit Monitor");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    title.setStyle("-fx-text-fill: white;");

    Label subtitle = new Label("Track your sleep patterns and build healthy daily habits");
    subtitle.setStyle("-fx-text-fill: #ecf0f1;");

    header.getChildren().addAll(title, subtitle);
    return header;
  }

  private TabPane createTabPane() {
    TabPane tabPane = new TabPane();
    tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

    Tab sleepTab = new Tab("Sleep Records", createSleepTab());
    Tab habitTab = new Tab("Daily Habits", createHabitTab());
    Tab statsTab = new Tab("Statistics", createStatsTab());

    tabPane.getTabs().addAll(sleepTab, habitTab, statsTab);
    return tabPane;
  }

  private VBox createSleepTab() {
    VBox vbox = new VBox(10);
    vbox.setPadding(new Insets(15));

    Label title = new Label("Sleep Records");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

    // Input section
    HBox inputBox = new HBox(10);
    inputBox.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-padding: 10px;");

    DatePicker datePicker = new DatePicker(LocalDate.now());
    Spinner<Double> hoursSpinner = new Spinner<>(0.0, 24.0, 8.0, 0.5);
    ComboBox<String> qualityCombo = new ComboBox<>();
    qualityCombo.getItems().addAll("poor", "fair", "good", "excellent");
    qualityCombo.setValue("good");

    Button addButton = new Button("Add Sleep Record");
    addButton.setStyle("-fx-padding: 8px 16px; -fx-font-size: 12;");
    addButton.setOnAction(
        e -> {
          try {
            SleepRecord record =
                new SleepRecord(datePicker.getValue(), hoursSpinner.getValue(), qualityCombo.getValue());
            tracker.addSleepRecord(record);
            saveCallback.run();
            refreshSleepList();
            showAlert("Success", "Sleep record added!");
          } catch (Exception ex) {
            showAlert("Error", ex.getMessage());
          }
        });

    inputBox.getChildren().addAll(
        new Label("Date:"),
        datePicker,
        new Label("Hours:"),
        hoursSpinner,
        new Label("Quality:"),
        qualityCombo,
        addButton);

    // List section
    Label listLabel = new Label("All Sleep Records:");
    listLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    sleepListView.setPrefHeight(300);
    refreshSleepList();

    Button deleteButton = new Button("Delete Selected");
    deleteButton.setStyle("-fx-padding: 6px 12px;");
    deleteButton.setOnAction(
        e -> {
          String selected = sleepListView.getSelectionModel().getSelectedItem();
          if (selected != null) {
            // Parse date from string and remove
            try {
              String dateStr = selected.split(" - ")[0];
              LocalDate date = LocalDate.parse(dateStr);
              tracker.removeSleepRecord(date);
              saveCallback.run();
              refreshSleepList();
              showAlert("Success", "Record deleted!");
            } catch (Exception ex) {
              showAlert("Error", "Failed to delete record");
            }
          }
        });

    vbox.getChildren().addAll(title, inputBox, listLabel, sleepListView, deleteButton);
    return vbox;
  }

  private VBox createHabitTab() {
    VBox vbox = new VBox(10);
    vbox.setPadding(new Insets(15));

    Label title = new Label("Daily Habits");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

    // Input section
    HBox inputBox = new HBox(10);
    inputBox.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-padding: 10px;");

    DatePicker datePicker = new DatePicker(LocalDate.now());
    TextField habitNameField = new TextField();
    habitNameField.setPromptText("Habit name (e.g., Exercise, Meditation)");
    habitNameField.setPrefWidth(200);

    CheckBox completedCheck = new CheckBox("Completed");
    completedCheck.setSelected(true);

    TextArea notesArea = new TextArea();
    notesArea.setPrefHeight(60);
    notesArea.setWrapText(true);
    notesArea.setPromptText("Optional notes...");

    Button addButton = new Button("Add Habit Entry");
    addButton.setStyle("-fx-padding: 8px 16px; -fx-font-size: 12;");
    addButton.setOnAction(
        e -> {
          try {
            String habitName = habitNameField.getText().trim();
            if (habitName.isEmpty()) {
              showAlert("Error", "Please enter a habit name");
              return;
            }
            HabitEntry entry =
                new HabitEntry(
                    datePicker.getValue(),
                    habitName,
                    completedCheck.isSelected(),
                    notesArea.getText());
            tracker.addHabitEntry(entry);
            saveCallback.run();
            refreshHabitList();
            habitNameField.clear();
            notesArea.clear();
            showAlert("Success", "Habit entry added!");
          } catch (Exception ex) {
            showAlert("Error", ex.getMessage());
          }
        });

    VBox inputVBox = new VBox(10);
    inputVBox.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-padding: 10px;");
    inputVBox.getChildren().addAll(
        new HBox(5, new Label("Date:"), datePicker),
        new HBox(5, new Label("Habit:"), habitNameField),
        new HBox(5, completedCheck),
        new Label("Notes:"),
        notesArea,
        addButton);

    // List section
    Label listLabel = new Label("All Habit Entries:");
    listLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

    habitListView.setPrefHeight(250);
    refreshHabitList();

    Button deleteButton = new Button("Delete Selected");
    deleteButton.setStyle("-fx-padding: 6px 12px;");
    deleteButton.setOnAction(
        e -> {
          String selected = habitListView.getSelectionModel().getSelectedItem();
          if (selected != null) {
            showAlert("Info", "Delete functionality can be enhanced in future versions");
          }
        });

    vbox.getChildren().addAll(title, inputVBox, listLabel, habitListView, deleteButton);
    return vbox;
  }

  private VBox createStatsTab() {
    VBox vbox = new VBox(15);
    vbox.setPadding(new Insets(15));
    vbox.setStyle("-fx-background-color: #ecf0f1;");

    Label title = new Label("Statistics & Insights");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

    // Sleep statistics
    VBox sleepStatsBox = createStatsBox("Sleep Analysis", "#3498db");
    Label avgSleep7 = new Label();
    Label avgSleep30 = new Label();

    Button refreshStatsButton = new Button("Refresh Statistics");
    refreshStatsButton.setStyle("-fx-padding: 8px 16px;");
    refreshStatsButton.setOnAction(
        e -> {
          double avg7 = tracker.getAverageSleepHours(7);
          double avg30 = tracker.getAverageSleepHours(30);
          avgSleep7.setText(String.format("📊 Average Sleep (Last 7 days): %.1f hours", avg7));
          avgSleep30.setText(String.format("📊 Average Sleep (Last 30 days): %.1f hours", avg30));
        });

    sleepStatsBox.getChildren().addAll(avgSleep7, avgSleep30);

    // Habit statistics
    VBox habitStatsBox = createStatsBox("Habit Tracking", "#2ecc71");
    Label exerciseCompletion = new Label();
    Label meditationCompletion = new Label();

    habitStatsBox.getChildren().addAll(
        new Label("🏃 Habit Completion Rates (Last 7 days):"),
        exerciseCompletion,
        meditationCompletion);

    Button refreshHabitStatsButton = new Button("Refresh Habit Stats");
    refreshHabitStatsButton.setStyle("-fx-padding: 8px 16px;");
    refreshHabitStatsButton.setOnAction(
        e -> {
          double exerciseRate = tracker.getHabitCompletionRate("Exercise", 7);
          double meditationRate = tracker.getHabitCompletionRate("Meditation", 7);
          exerciseCompletion.setText(String.format("  • Exercise: %.0f%%", exerciseRate));
          meditationCompletion.setText(String.format("  • Meditation: %.0f%%", meditationRate));
        });

    // Info box
    VBox infoBox = createStatsBox("Tips for Better Sleep", "#e74c3c");
    Label tip1 = new Label("✓ Maintain a consistent sleep schedule");
    Label tip2 = new Label("✓ Keep your bedroom cool and dark");
    Label tip3 = new Label("✓ Avoid screens 30 minutes before bed");
    Label tip4 = new Label("✓ Regular exercise improves sleep quality");
    infoBox.getChildren().addAll(tip1, tip2, tip3, tip4);

    ScrollPane scrollPane = new ScrollPane();
    VBox contentBox = new VBox(15);
    contentBox.setPadding(new Insets(10));
    contentBox.getChildren().addAll(
        title,
        refreshStatsButton,
        sleepStatsBox,
        refreshHabitStatsButton,
        habitStatsBox,
        infoBox);
    scrollPane.setContent(contentBox);
    scrollPane.setFitToWidth(true);

    vbox.getChildren().add(scrollPane);
    VBox.setVgrow(scrollPane, Priority.ALWAYS);

    return vbox;
  }

  private VBox createStatsBox(String title, String color) {
    VBox box = new VBox(8);
    box.setStyle(
        String.format(
            "-fx-border-color: %s; -fx-border-radius: 5; -fx-padding: 12px; -fx-background-color: white;",
            color));

    Label titleLabel = new Label(title);
    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    titleLabel.setStyle("-fx-text-fill: " + color + ";");

    box.getChildren().add(titleLabel);
    return box;
  }

  private void refreshSleepList() {
    sleepListView.getItems().clear();
    for (SleepRecord record : tracker.getSleepRecords()) {
      sleepListView
          .getItems()
          .add(
              String.format(
                  "%s - %.1f hours (%s)",
                  record.getDate(), record.getHours(), record.getQuality()));
    }
  }

  private void refreshHabitList() {
    habitListView.getItems().clear();
    for (HabitEntry entry : tracker.getHabitEntries()) {
      String status = entry.isCompleted() ? "✓" : "✗";
      habitListView
          .getItems()
          .add(
              String.format(
                  "[%s] %s - %s (%s)", status, entry.getDate(), entry.getHabitName(), entry.getNotes()));
    }
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public BorderPane getRoot() {
    return root;
  }
}
