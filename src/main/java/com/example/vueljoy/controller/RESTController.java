package com.example.vueljoy.controller;

import com.example.vueljoy.model.User;
import com.example.vueljoy.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class RESTController {

    private final UserService userService;

    @GetMapping("/game")
    public void createCustomer() throws JsonProcessingException, InterruptedException {
        userService.start();
    }

}
