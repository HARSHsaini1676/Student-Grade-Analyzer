# Student Grade Analyzer

A simple, fast, and dependency-free modern Java application that parses CSV data of student grades and generates statistical reports using the Java Streams API.

## Features

- **Top Performers list:** Filters students based on a custom threshold and sorts passing students by mark.
- **Subject Averages:** Calculates average mark for every subject.
- **Student Averages:** Groups marks by roll number and calculates personal averages, while also reporting incomplete subject tracking (e.g. if a student took fewer than the total number of subjects).
- **Stream-based processing:** Utilizes modern Java `stream()` functionality for grouping and aggregating data.
- **Data Validation:** Safely skips corrupt rows with empty fields using custom exception handling.

## Architecture

The project follows a standard layered modular architecture without requiring external DI frameworks:
- **Model (`com.harsh.gradeanalyzer.model`)**: Domain POJOs built with Java `record` for immutability.
- **IO (`com.harsh.gradeanalyzer.io`)**: Parsing logic.
- **Service (`com.harsh.gradeanalyzer.service`)**: Business logic using `Collectors.groupingBy` and other aggregation capabilities.
- **Report (`com.harsh.gradeanalyzer.report`)**: Standard output formatters.

## Getting Started

### Prerequisites
- JDK 17+ (Features in this project heavily utilize modern constructs like records which were fully introduced in JDK 16).

### Building and Running

This project does not use a build framework (like Maven or Gradle). It can be run from the command line using pure Java commands.

1. **Compile the program:**
   ```bash
   javac -d out $(find src -name "*.java")
   ```

2. **Run the program:**
   You must ensure the CSV resource `students.csv` is in the classpath.
   ```bash
   cp src/students.csv out/students.csv
   java -cp out com.harsh.gradeanalyzer.StudentGradeAnalyzer
   ```

### Output Example
```
Parsed 242 rows, skipped 8
=== Top Performers ===
 1. Harsh Singh          10A    ComputerScience    97
 2. Myra Kumar           10A    English            97
 ...
=== Average Marks by Subject ===
ComputerScience      66.51
English              66.92
...
=== Average Marks by Student (Roll No) ===
Roll 1    66.20
Roll 2    65.40
...
Roll 11   81.00 (incomplete: 3/5 subjects)
```

## Future Enhancements
- Add JUnit testing to assert stream behaviors.
- Migrate to a build tool (Maven/Gradle) to easily run tests and package executable JAR files.
