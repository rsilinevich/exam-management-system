package com.example.exammanagementsystem.repository;

import com.example.exammanagementsystem.model.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRecordRepository extends JpaRepository<ExamRecord, Long> {

    List<ExamRecord> findAllByOrderByIdAsc();

    boolean existsByStudentId(Long studentId);

    boolean existsByExamId(Long examId);
}