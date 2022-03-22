package com.example.codingbat.repository;

import com.example.codingbat.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    boolean existsByQuestion(String question);
}