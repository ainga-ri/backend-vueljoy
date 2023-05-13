package com.example.vueljoy.service;

import com.example.vueljoy.controller.dto.PlayerDTO;
import com.example.vueljoy.controller.dto.UserDTO;
import com.example.vueljoy.model.Player;
import com.example.vueljoy.model.User;
import com.example.vueljoy.model.send.Register;
import com.example.vueljoy.repository.PlayerRepository;
import com.example.vueljoy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    public String registerUser(UserDTO userDTO) {
        User user =  new User(userDTO.getName(), userDTO.getSeat());
        //todo handle errors with duplicates
        userRepository.save(user);
        return (String.format("""
                Hello %s seating in %s
                """, user.getName(), user.getSeat()));
    }

    public String welcomeUser(UserDTO userDTO) {
        return (String.format("""
                Hi bro, you are %s and you are seated in %s, but is a secret
                """, userDTO.getName(), userDTO.getSeat()));
    }

    public String submitAnswer(PlayerDTO playerDTO) {
        // Check if the user is already registered
        var userExists = userRepository.findBySeat(playerDTO.getSeat());
        if (userExists.isEmpty()) {
            return null; //todo handle errors
        }
        // Check if the answer is correct
        if (playerDTO.isCorrect()) {
            // check if the player is already in score, like for example, all in the first question
            var player = playerRepository.findBySeat(playerDTO.getSeat());
            if (player.isEmpty())
                playerRepository.save(new Player(playerDTO.getSeat(), 1));
            else {
                player.get().setScore(player.get().getScore() + 1);
                playerRepository.save(player.get());
            }
        } else {
            var player = playerRepository.findBySeat(playerDTO.getSeat());
            if (player.isEmpty())
                playerRepository.save(new Player(playerDTO.getSeat(), 0));
        }
        return null;
    }


}
