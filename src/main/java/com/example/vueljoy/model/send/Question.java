package com.example.vueljoy.model.send;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

//@JsonPropertyOrder({ "type", "q", "answers" })
@Data
@JsonDeserialize(using = QuestionDeserializer.class)
public class Question {
    private String type = "q";
    private String q;

    private String[] answers;

    public Question( String q, String[] answers) {

        this.q = q;
        this.answers = answers;
    }
}
