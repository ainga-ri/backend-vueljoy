package com.example.vueljoy.controller;

import com.example.vueljoy.controller.dto.PlayerDTO;
import com.example.vueljoy.controller.dto.UserDTO;
import com.example.vueljoy.model.send.Register;
import com.example.vueljoy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class WebSocketsController {

    private final UserService userService;

    @MessageMapping("/register")
    @SendTo("/topic/broadcast")
    public Register register(UserDTO userDTO) throws Exception {
        return new Register(userService.registerUser(userDTO));
    }

    @MessageMapping("/welcome")
    @SendToUser("/queue/welcome")
    public Register welcome(UserDTO userDTO) throws Exception {
        //Thread.sleep(1000); // simulated delay
        return new Register(userService.welcomeUser(userDTO));

    }
    @MessageMapping("/submit-answer")
    @SendTo("/topic/broadcast")
    public Register submitAnswer(PlayerDTO playerDTO) throws Exception {
        return new Register(userService.submitAnswer(playerDTO));
    }
}




