package com.example.vueljoy.model;

import lombok.Data;

@Data
public class Question {
    private String type = "q";
    private int questionNumber;
    public Question(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}

