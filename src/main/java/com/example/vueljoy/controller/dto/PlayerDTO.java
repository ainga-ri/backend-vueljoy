package com.example.vueljoy.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDTO {
    private String seat;
    private boolean isCorrect;
    private String time;
}
