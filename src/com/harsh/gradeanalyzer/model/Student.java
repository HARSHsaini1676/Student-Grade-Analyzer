package com.harsh.gradeanalyzer.model;

public record Student(
        String name,
        int rollNo,
        String className,
        String subject,
        int marks
){}