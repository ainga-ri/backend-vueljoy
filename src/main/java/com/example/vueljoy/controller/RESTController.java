package com.example.vueljoy.controller;

import com.example.vueljoy.model.User;
import com.example.vueljoy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class RESTController {

    private final UserService userService;
    /*
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody User user) {
        return userService.registerUser(user);
    }
    */
}
