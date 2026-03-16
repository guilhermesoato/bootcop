# 🌙 Sleep Tracker - Daily Habit Monitor

A professional desktop application built with JavaFX for monitoring sleep patterns and tracking daily habits, helping users improve their health and well-being.

**Version:** 1.0.0 | **Status:** ✅ Production Ready

---

## 📋 Table of Contents

- [Problem Statement](#problem-statement)
- [Solution Overview](#solution-overview)
- [Target Audience](#target-audience)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [Running Linting](#running-linting)
- [CI/CD Pipeline](#cicd-pipeline)
- [Usage Guide](#usage-guide)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

---

## 🎯 Problem Statement

**The Real Problem:**

Many people struggle with inconsistent sleep patterns and lack of awareness about their daily habits. This often leads to:
- Sleep deprivation and poor sleep quality
- Difficulty establishing healthy routines
- Lack of insight into what behaviors affect sleep
- No systematic way to track progress or identify patterns

Individuals, especially students, professionals, and seniors, need a simple, intuitive tool to:
- Record and monitor sleep duration and quality
- Track daily habits (exercise, meditation, hydration)
- Visualize patterns and progress
- Make data-driven decisions about their health

---

## 💡 Solution Overview

**Sleep Tracker** is a lightweight desktop application that provides:

1. **Sleep Recording** - Log daily sleep duration and quality
2. **Habit Tracking** - Monitor important daily habits (exercise, meditation, hydration)
3. **Statistical Analysis** - View average sleep hours and habit completion rates
4. **Data Persistence** - Automatically save all data locally

The application uses a clean, intuitive GUI that requires no technical knowledge to use. Data is stored locally as JSON files, ensuring privacy and offline availability.

---

## 👥 Target Audience

- 🧑‍💼 **Professionals** seeking better sleep hygiene and work-life balance
- 👨‍🎓 **Students** managing stress and establishing healthy routines
- 👵 **Seniors** and caregivers monitoring sleep patterns for health management
- 🏃 **Athletes** optimizing recovery and performance through sleep tracking
- 🧘 **Health-conscious individuals** building and maintaining positive habits

---

## ✨ Key Features

### 1. Sleep Record Management
- Add date, hours slept, and sleep quality rating
- Automatically validates input (0-24 hours, valid quality levels)
- View all historical sleep records in an organized list
- Delete specific sleep records

### 2. Daily Habit Tracking
- Create custom habit entries for any day
- Mark habits as completed or incomplete
- Add optional notes for context
- View all habit entries organized by date

### 3. Statistics Dashboard
- Calculate average sleep hours (last 7 and 30 days)
- Track habit completion rates
- View helpful tips for sleep improvement
- Real-time statistics updates

### 4. Data Persistence
- Automatic saving on application exit
- Manual save option
- JSON-based local storage (no server required)
- Easy data backup and transfer

---

## 🛠️ Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 17+ |
| **Build Tool** | Maven | 3.8+ |
| **GUI Framework** | JavaFX | 21.0.1 |
| **Testing** | JUnit 5 | 5.10.0 |
| **Code Quality** | Checkstyle | 10.12.4 |
| **Assertions** | AssertJ | 3.24.1 |
| **Data Format** | JSON (GSON) | 2.10.1 |
| **CI/CD** | GitHub Actions | - |

---

## 📁 Project Structure

```
sleep-tracker/
├── src/
│   ├── main/
│   │   ├── java/com/habitsleep/
│   │   │   ├── SleepTrackerApp.java          # Main application entry point
│   │   │   ├── model/
│   │   │   │   ├── SleepRecord.java          # Sleep data model
│   │   │   │   ├── HabitEntry.java           # Habit data model
│   │   │   │   └── HabitTracker.java         # Core business logic
│   │   │   ├── ui/
│   │   │   │   └── SleepTrackerUI.java       # JavaFX GUI implementation
│   │   │   └── util/
│   │   │       └── DataPersistence.java      # JSON serialization/deserialization
│   │   └── resources/                        # Static resources
│   └── test/
│       └── java/com/habitsleep/
│           └── model/
│               ├── SleepRecordTest.java      # Unit tests for SleepRecord
│               ├── HabitEntryTest.java       # Unit tests for HabitEntry
│               └── HabitTrackerTest.java     # Unit tests for HabitTracker
├── .github/
│   └── workflows/
│       └── ci.yml                            # GitHub Actions CI pipeline
├── pom.xml                                   # Maven configuration & dependencies
├── checkstyle.xml                            # Code quality rules
├── .gitignore                                # Git ignore patterns
├── README.md                                 # Project documentation (this file)
└── LICENSE                                   # MIT License

```

---

## 📦 Installation

### Prerequisites

- **Java Development Kit (JDK) 17 or higher**
  - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or use OpenJDK
  - Verify: `java -version`

- **Maven 3.8.1 or higher**
  - Download from [Maven Official Site](https://maven.apache.org/download.cgi)
  - Verify: `mvn -v`

- **Git** (optional, for cloning the repository)
  - Download from [Git Official Site](https://git-scm.com/)

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/sleep-tracker.git
   cd sleep-tracker
   ```

2. **Verify Maven Setup**
   ```bash
   mvn clean compile
   ```
   This command will:
   - Download all dependencies
   - Compile the source code
   - Prepare the project for building

3. **Build the Project**
   ```bash
   mvn clean package
   ```
   This creates an executable JAR file in the `target/` directory.

---

## 🚀 Running the Application

### Option 1: Run Directly with Maven
```bash
mvn clean javafx:run
```

### Option 2: Run Compiled JAR
```bash
# First, build the project
mvn clean package

# Then run the JAR
java -jar target/sleep-tracker-1.0.0.jar
```

### Option 3: Run from IDE
- Import the project into IntelliJ IDEA, Eclipse, or VS Code
- Ensure JDK 17+ is configured as the project SDK
- Run `SleepTrackerApp.java` as a Java application

### First Launch
On first launch, the application will:
1. Create a `habit_tracker_data/` directory in your home folder
2. Initialize empty data files for sleep records and habits
3. Display an empty interface ready for data entry

---

## 🧪 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn test -Dtest=HabitTrackerTest
```

### Run Tests with Output Report
```bash
mvn clean test -X
```

### Test Coverage
The project includes **30+ unit tests** covering:

- ✅ **SleepRecord** (10 tests)
  - Valid record creation
  - Invalid input validation (negative hours, invalid quality)
  - Setter validation
  - All quality value combinations

- ✅ **HabitEntry** (8 tests)
  - Entry creation and validation
  - Empty/null habit name handling
  - Setter operations
  - Notes management

- ✅ **HabitTracker** (15+ tests)
  - Add/remove operations
  - Average sleep calculation
  - Habit completion rate calculation
  - Date filtering and statistics
  - Edge cases (no records, invalid parameters)

### Test Results
All tests should pass with output like:
```
[INFO] Tests run: 33, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 🔍 Running Linting

### Check Code Quality
```bash
mvn checkstyle:check
```

### Generate Detailed Report
```bash
mvn checkstyle:checkstyle
```
Report is generated at: `target/site/checkstyle.html`

### Rules Enforced
- **Naming Conventions**: Classes (PascalCase), methods/variables (camelCase)
- **Documentation**: Javadoc for public classes and methods
- **Whitespace**: Consistent spacing and formatting
- **Imports**: No star imports, removal of unused imports
- **Complexity**: Cyclomatic complexity limited to 10 per method
- **Line Length**: Maximum 120 characters
- **Code Organization**: Proper structure and readability

### Fixing Violations
Most violations can be fixed with:
- Proper naming conventions
- Adding Javadoc comments
- Adjusting whitespace
- Simplifying complex methods

---

## 🔄 CI/CD Pipeline

### GitHub Actions Workflow

The project includes an automated CI pipeline that runs on:
- **When:** Every push to `main` or `develop` branch
- **When:** Every pull request

### Pipeline Stages

```
1. Setup Environment
   └─ Install JDK 17
   └─ Verify Maven installation

2. Build
   └─ Clean and compile sources
   └─ Resolve all dependencies

3. Code Quality
   └─ Run Checkstyle linting
   └─ Generate code quality reports

4. Test
   └─ Execute all unit tests
   └─ Generate test reports

5. Package
   └─ Create executable JAR
   └─ Verify build artifacts
```

### Viewing Pipeline Status

1. Go to your GitHub repository
2. Click **Actions** tab
3. Select the workflow run
4. View detailed logs and results

### Build Badge
Add this to your README for a status badge:
```markdown
![Build Status](https://github.com/yourusername/sleep-tracker/workflows/CI/badge.svg)
```

---

## 📖 Usage Guide

### Starting the Application

1. Launch the application using one of the methods above
2. The main window displays three tabs: Sleep Records, Daily Habits, and Statistics

### Sleep Records Tab

**Adding a Sleep Record:**
1. Select a date from the date picker
2. Enter hours slept (0-24 hours)
3. Select sleep quality: "poor", "fair", "good", or "excellent"
4. Click **Add Sleep Record**
5. Confirm the success message

**Viewing Records:**
- All records appear in the list below
- Format: `YYYY-MM-DD - X.X hours (quality)`

**Deleting Records:**
1. Select a record from the list
2. Click **Delete Selected**
3. Confirm deletion

### Daily Habits Tab

**Adding a Habit Entry:**
1. Select a date (default: today)
2. Enter habit name (e.g., "Exercise", "Meditation", "Hydration")
3. Check "Completed" if the habit was done that day
4. Add optional notes for context
5. Click **Add Habit Entry**

**Viewing Entries:**
- All entries appear in the list
- Format: `[✓/✗] YYYY-MM-DD - HabitName (notes)`

**Tracking Multiple Habits:**
- Create different habit entries for different activities
- Track the same habit across multiple days
- Different completion status for different days

### Statistics Tab

**View Your Progress:**
1. Click the **Refresh Statistics** button
2. See average sleep hours for:
   - Last 7 days
   - Last 30 days

3. Click **Refresh Habit Stats** button
4. View habit completion percentages (e.g., "Exercise: 75%")

**Sleep Tips:**
- The tab displays evidence-based recommendations for better sleep

### Data Persistence

- All data is **automatically saved** when you close the application
- Data is stored locally in `habit_tracker_data/` folder as JSON
- No internet connection required
- Backup your `habit_tracker_data/` folder for safekeeping

---

## 🤝 Contributing

We welcome contributions! Follow these steps:

1. **Fork the Repository**
   ```bash
   git clone https://github.com/yourusername/sleep-tracker.git
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Make Your Changes**
   - Follow code style guidelines
   - Add tests for new features
   - Update documentation

4. **Run Quality Checks**
   ```bash
   mvn clean package
   ```
   Ensure all tests pass and linting succeeds.

5. **Commit and Push**
   ```bash
   git add .
   git commit -m "feat: Add your feature description"
   git push origin feature/your-feature-name
   ```

6. **Submit a Pull Request**
   - Describe your changes clearly
   - Reference any related issues
   - Ensure CI pipeline passes

### Code Style Guidelines
- Follow Java naming conventions
- Use camelCase for methods and variables
- Use PascalCase for classes
- Add Javadoc for public methods
- Keep methods focused and simple
- Maximum cyclomatic complexity: 10

---

## 📄 License

This project is licensed under the **MIT License** - see [LICENSE](LICENSE) file for details.

You are free to:
- ✅ Use commercially
- ✅ Modify the code
- ✅ Distribute
- ✅ Use privately

Under the condition that you include a copy of the license.

---

## 👤 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

---

## 📞 Support & Feedback

- **Found a bug?** Open an [Issue](https://github.com/yourusername/sleep-tracker/issues)
- **Have a feature idea?** Start a [Discussion](https://github.com/yourusername/sleep-tracker/discussions)
- **Want to contribute?** See [Contributing](#contributing) section

---

## 🎓 Learning Resources

This project demonstrates:
- ✅ Professional Java development practices
- ✅ Object-oriented design principles
- ✅ JavaFX GUI development
- ✅ Unit testing with JUnit 5
- ✅ Code quality analysis with Checkstyle
- ✅ CI/CD automation with GitHub Actions
- ✅ Version control with Git
- ✅ Semantic versioning
- ✅ Professional documentation

---

## 📊 Project Statistics

- **Total Lines of Code**: ~1,500
- **Test Coverage**: 30+ unit tests
- **Code Quality Rules**: 15+ Checkstyle checks
- **Build Reproducibility**: 100% (Maven + GitHub Actions)
- **Documentation**: Comprehensive README + Javadoc

---

**Last Updated:** March 2026
