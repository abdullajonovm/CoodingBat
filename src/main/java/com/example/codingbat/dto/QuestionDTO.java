package com.example.codingbat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class QuestionDTO {
    private String question;
    private Integer categoryId;
}
