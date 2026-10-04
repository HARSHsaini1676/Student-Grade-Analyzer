package com.harsh.gradeanalyzer.report;

import com.harsh.gradeanalyzer.model.Student;

import java.util.List;
import java.util.Map;

public class ReportPrinter {

    public void printSubjectAverages(Map<String, Double> subjectAverages) {
        System.out.println("=== Average Marks by Subject ===");
        subjectAverages.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.printf("%-20s %.2f%n", entry.getKey(), entry.getValue())
                );
    }

    public void printStudentAverages(Map<Integer, Double> studentAverages, Map<Integer, Long> subjectCounts) {
        System.out.println("=== Average Marks by Student (Roll No) ===");
        studentAverages.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    int rollNo = entry.getKey();
                    double avg = entry.getValue();
                    long count = subjectCounts.getOrDefault(rollNo, 0L);
                    String note = (count < 5) ? String.format(" (incomplete: %d/5 subjects)", count) : "";
                    System.out.printf("Roll %-4d %.2f%s%n", rollNo, avg, note);
                });
    }

    public void printTopPerformers(List<Student> topPerformers) {
        System.out.println("=== Top Performers ===");
        int rank = 1;
        for (Student s : topPerformers) {
            System.out.printf("%2d. %-20s %-6s %-18s %d%n",
                    rank++, s.name(), s.className(), s.subject(), s.marks());
        }
    }
}