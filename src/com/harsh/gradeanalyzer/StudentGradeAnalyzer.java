package com.harsh.gradeanalyzer;

import com.harsh.gradeanalyzer.io.CsvReader;
import com.harsh.gradeanalyzer.model.Student;
import com.harsh.gradeanalyzer.report.ReportPrinter;
import com.harsh.gradeanalyzer.service.GradeAnalyzer;

import java.util.List;

public class StudentGradeAnalyzer {
    public static void main(String[] args) {
        CsvReader reader = new CsvReader();
        List<Student> students = reader.readAll("students.csv");

        GradeAnalyzer analyzer = new GradeAnalyzer(students);
        ReportPrinter printer = new ReportPrinter();

        printer.printTopPerformers(analyzer.topPerformers(85));
        printer.printSubjectAverages(analyzer.averageMarksBySubject());
        printer.printStudentAverages(analyzer.averageMarksByStudent(), analyzer.subjectCountByStudent());
    }
}
