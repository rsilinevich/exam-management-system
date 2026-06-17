package com.example.exammanagementsystem.repository;

import com.example.exammanagementsystem.model.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRecordRepository extends JpaRepository<ExamRecord, Long> {
}
