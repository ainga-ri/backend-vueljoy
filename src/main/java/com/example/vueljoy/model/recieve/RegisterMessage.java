package com.example.vueljoy.model.recieve;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMessage {
    private String seatID;

    private String username;
}
