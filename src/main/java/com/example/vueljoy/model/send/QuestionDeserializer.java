//package com.example.vueljoy.model.send;
//
//import com.example.vueljoy.model.Question;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.IntNode;
//
//import java.io.IOException;
//
//public class QuestionDeserializer extends JsonDeserializer<Question> {
//    @Override
//    public Question deserialize(JsonParser jp, DeserializationContext ctxt)
//            throws IOException, JsonProcessingException {
//        JsonNode node = jp.getCodec().readTree(jp);
//        int id = (Integer) ((IntNode) node.get("id")).numberValue();
//        String itemName = node.get("itemName").asText();
//        int userId = (Integer) ((IntNode) node.get("createdBy")).numberValue();
//
//        return new Question(1);
//    }
//}
