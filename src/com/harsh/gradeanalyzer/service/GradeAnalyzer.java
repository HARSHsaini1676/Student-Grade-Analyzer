package com.harsh.gradeanalyzer.service;

import com.harsh.gradeanalyzer.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeAnalyzer {

    private final List<Student> students;

    public GradeAnalyzer(List<Student> students) {
        this.students = students;
    }

    public List<Student> topPerformers(double threshold) {
        return students.stream()
                .filter(s -> s.marks() > threshold)
                .sorted(Comparator.comparingInt(Student::marks).reversed())
                .collect(Collectors.toList());

    }

    public Map<String, List<Student>> groupBySubject() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::subject));
    }

    public Map<String, Double> averageMarksBySubject() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::subject, Collectors.averagingDouble(Student::marks)));
    }

    public Map<String, List<Student>> groupByClassName() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::className));
    }

    public Map<Integer, Double> averageMarksByStudent() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::rollNo, Collectors.averagingDouble(Student::marks)));
    }

    public Map<Integer, Long> subjectCountByStudent() {
        return students.stream().collect(Collectors.groupingBy(Student::rollNo, Collectors.counting()));
    }
}