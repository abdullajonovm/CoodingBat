package com.example.codingbat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestingOptionsDTO {
    private Object input;
    private Object output;
    private Integer questionId;
}
