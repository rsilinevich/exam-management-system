package com.example.exammanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity // Tells Hibernate to make a table out of this class
@Table(name = "exams") // Shows table name in database
public class Exam {
    @Id // Marks field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @NotBlank(message = "Exam title is required")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "Exam date is required")
    @FutureOrPresent(message = "Date must be in today or in the future")
    private LocalDate date;

    @NotBlank(message = "Professor name is required")
    private String professor;
}
