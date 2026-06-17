package com.example.exammanagementsystem.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamRecordRequest {
    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @Min(value = 0, message = "Grade cannot be less than 0")
    @Max(value = 10, message = "Grade cannot be more than 10")
    private Integer grade;
}
