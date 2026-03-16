# Contributing to Sleep Tracker

Thank you for your interest in contributing to the Sleep Tracker project! We welcome contributions from everyone. This document provides guidelines and instructions for contributing.

## Code of Conduct

We are committed to providing a welcoming and inclusive environment for all contributors.
- Be respectful and professional
- Provide constructive feedback
- Focus on the work, not the person
- Help each other learn and grow

## How to Contribute

### Reporting Bugs

Before creating a bug report, please check the issue list as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps which reproduce the problem**
- **Describe the behavior you observed after following the steps**
- **Explain what behavior you expected to see instead and why**
- **Include screenshots or animated GIF if possible**
- **Include your environment details** (OS, Java version, etc.)

### Suggesting Enhancements

If you have ideas for improvements or new features, please open an issue describing:

- **Use a clear and descriptive title**
- **Provide a step-by-step description of the suggested enhancement**
- **Provide specific examples to demonstrate the steps**
- **Explain why this enhancement would be useful**
- **List any similar features in other applications**

### Pull Requests

1. **Fork the Repository**
   ```bash
   git clone https://github.com/yourusername/sleep-tracker.git
   cd sleep-tracker
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```
   Use prefixes:
   - `feature/` for new features
   - `bugfix/` for bug fixes
   - `docs/` for documentation
   - `test/` for tests
   - `chore/` for maintenance

3. **Make Your Changes**
   - Write clean, readable code
   - Add or update tests for your changes
   - Follow the code style guidelines (see below)
   - Update documentation if needed

4. **Verify Your Changes**
   ```bash
   # Run all tests
   mvn clean test
   
   # Check code quality
   mvn checkstyle:check
   
   # Build everything
   mvn clean package
   ```
   All checks must pass before submitting.

5. **Commit Your Changes**
   ```bash
   git add .
   git commit -m "type: Brief description"
   ```
   Commit message format:
   - `feat:` - Feature
   - `fix:` - Bug fix
   - `docs:` - Documentation
   - `test:` - Tests
   - `refactor:` - Code refactoring
   - `chore:` - Build/maintenance
   
   Example: `feat: Add export to CSV functionality`

6. **Push to Your Fork**
   ```bash
   git push origin feature/your-feature-name
   ```

7. **Submit a Pull Request**
   - Go to the original repository
   - Click "New Pull Request"
   - Select your branch
   - Fill in the PR template with:
     - Description of changes
     - Related issue numbers (#123)
     - Screenshots for UI changes
     - Note on testing

### Code Style Guidelines

#### Java Naming Conventions
- **Classes**: `PascalCase` (e.g., `SleepRecord`)
- **Methods**: `camelCase` (e.g., `addSleepRecord()`)
- **Variables**: `camelCase` (e.g., `sleepHours`)
- **Constants**: `UPPER_SNAKE_CASE` (e.g., `MAX_SLEEP_HOURS`)

#### Code Organization
```java
public class Example {
  // 1. Constants and static fields
  private static final int MAX_SIZE = 100;
  
  // 2. Instance fields
  private String name;
  
  // 3. Constructors
  public Example(String name) {
    this.name = name;
  }
  
  // 4. Public methods
  public void start() {
    // Implementation
  }
  
  // 5. Private methods
  private void helper() {
    // Implementation
  }
}
```

#### Documentation
```java
/**
 * Short description of what the method does.
 *
 * @param paramName description of parameter
 * @return description of return value
 * @throws ExceptionType when this exception is thrown
 */
public void methodName(String paramName) {
  // Implementation
}
```

#### Formatting
- Use 2-space indentation (or configure your IDE for Maven defaults)
- Maximum line length: 120 characters
- Use meaningful variable names
- Remove unused imports
- No star imports

### Testing Guidelines

#### Writing Tests
- Write tests for all new features
- Use descriptive test method names: `testFeatureX()`, `testInvalidInput()`
- Organize tests by class/feature
- Follow the Arrange-Act-Assert pattern

```java
@Test
void testAddition() {
  // Arrange
  Calculator calc = new Calculator();
  
  // Act
  int result = calc.add(2, 3);
  
  // Assert
  assertEquals(5, result);
}
```

#### Running Tests
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=HabitTrackerTest

# Run with coverage report
mvn clean test

# Run with detailed output
mvn test -X
```

#### Test Coverage
- Aim for at least 70% coverage of core business logic
- Test happy paths (normal usage)
- Test error cases (invalid input)
- Test edge cases (boundaries, empty collections)

### Documentation

When adding new features, please update:
- **README.md** - Add to features section if user-facing
- **Javadoc** - Add or update class/method documentation
- **CHANGELOG.md** - Add to unreleased section

### Development Workflow

1. Ensure your `main` branch is up to date
   ```bash
   git fetch upstream
   git checkout main
   git merge upstream/main
   ```

2. Create and work on your feature branch
3. Make regular, meaningful commits
4. Keep your branch up to date
5. Before submitting PR, resolve any conflicts

### CI/CD Pipeline

All pull requests automatically run:
- ✅ Maven build and compilation
- ✅ Checkstyle code quality checks
- ✅ All unit tests
- ✅ Package creation

All checks must pass for PR approval.

### Getting Help

- **Questions about contributing?** Open a discussion or issue
- **Need help getting started?** Ask in an issue
- **Found a documentation issue?** Edit and submit a PR

## Additional Resources

- [Java Coding Standards](https://www.oracle.com/java/technologies/javase/codeconventions-136091.html)
- [Maven Documentation](https://maven.apache.org/guides/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Checkstyle Documentation](https://checkstyle.sourceforge.io/)

## Recognition

Contributors will be recognized in:
- README.md contributors section
- GitHub contributors page
- Release notes

Thank you for contributing to Sleep Tracker! 🌙
