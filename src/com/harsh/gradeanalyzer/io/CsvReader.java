package com.harsh.gradeanalyzer.io;

import com.harsh.gradeanalyzer.model.Student;
import com.harsh.gradeanalyzer.exception.InvalidMarksException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CsvReader {

    public List<Student> readAll(String resourcePath) {
        List<Student> students = new ArrayList<>();
        int skippedCount = 0;

        InputStream is = CsvReader.class.getClassLoader().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new RuntimeException("Resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            List<String> lines = reader.lines().toList();

            // skip header row (index 0)
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                try {
                    Student s = parseLine(line);
                    students.add(s);
                } catch (InvalidMarksException e) {
                    skippedCount++;
                    System.out.println("Skipped row " + (i + 1) + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV: " + resourcePath, e);
        }

        System.out.println("Parsed " + students.size() + " rows, skipped " + skippedCount);
        return students;
    }

    private Student parseLine(String line) throws InvalidMarksException {
        String[] parts = line.split(",", -1);
        // parts[0] = name, parts[1] = rollNo, parts[2] = className, parts[3] = subject, parts[4] = marks

        String name = parts[0].trim();
        int rollNo = Integer.parseInt(parts[1]);
        String className = parts[2].trim();
        String subject = parts[3].trim();
        String marks = parts[4].trim();
        if (marks.isEmpty()) throw new InvalidMarksException("Marks field is empty in row: " + line);
        int mark = Integer.parseInt(marks);
        Student student = new Student(name, rollNo,className, subject, mark);

        return student;
    }
}