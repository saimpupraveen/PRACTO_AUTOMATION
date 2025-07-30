# PRACTO_AUTOMATION

## Overview
Selenium is an automation framework for automating and validating workflows on web applications, including functional and regression testing. This project focuses on automating and testing the Practo website using Java, Maven, TestNG, and Log4j for browser automation, reporting, and logging. All code is organized under test classes and utilities for Practo features.

## Features
- Automated browser testing using Selenium WebDriver
- TestNG for test management and reporting
- Log4j2 for logging
- Excel integration for test data input/output
- Page Object Model design pattern

## Project Structure
- `src/test/java` - Test cases, page objects, and utilities
- `src/test/resources` - Resource files (e.g., log4j2.xml)
- `reports/` - Test reports
- `screenshots/` - Screenshots captured during tests
- `TestInput/` - Excel files for test data
- `TestOutput/` - Excel files for test results
- `logs/` - Log files

## Dependencies
Managed via Maven (`pom.xml`):
- Selenium Java
- Apache POI (Excel)
- TestNG
- Log4j2

## Setup Instructions
1. Install Java 8 or above.
2. Install Maven.
3. Clone this repository.
4. Install browser drivers (ChromeDriver, EdgeDriver) and ensure they are in your PATH.
5. Run `mvn clean install` to build the project and download dependencies.

## Running Tests
- Use TestNG XML (`src/test/java/com/tests/testng.xml`) to configure and run tests.
- Run tests via Maven:
  ```
  mvn test
  ```
- Test reports are generated in the `test-output/` and `reports/` directories.

## Usage
- Update test data in `TestInput/CorporateWellnessPageData.xlsx`.
- Review results in `TestOutput/TestExecutionLogs.xlsx` and HTML reports.
- Screenshots for failed tests are saved in `screenshots/`.

## Author
Saimpu Praveen

## License
This project is for educational and internal automation purposes.
