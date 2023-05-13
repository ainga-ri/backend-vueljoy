package com.example.vueljoy.controller;

import com.example.vueljoy.controller.dto.PlayerDTO;
import com.example.vueljoy.controller.dto.UserDTO;
import com.example.vueljoy.model.User;
import com.example.vueljoy.model.send.Greeting;
import com.example.vueljoy.model.recieve.HelloMessage;
import com.example.vueljoy.model.send.Register;
import com.example.vueljoy.model.recieve.RegisterMessage;
import com.example.vueljoy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@Controller
@AllArgsConstructor
public class WebSocketsController {

    private final UserService userService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/register")
    @SendTo("/topic/greetings")
    public Register register(UserDTO userDTO) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Register(userService.registerUser(userDTO));
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.get) + "!");
    }

    @MessageMapping("/welcome")
    @SendToUser("/queue/welcome")
    public Register welcome(UserDTO userDTO) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Register(userService.welcomeUser(userDTO));
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.get) + "!");
    }

    @MessageMapping("/submit-answer")
    @SendTo("/topic/greetings")
    public Register submitAnswer(PlayerDTO playerDTO) throws Exception {

        return new Register(userService.submitAnswer(playerDTO));
        //return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.get) + "!");
    }
}




