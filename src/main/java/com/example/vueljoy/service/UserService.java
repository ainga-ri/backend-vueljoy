package com.example.vueljoy.service;

import com.example.vueljoy.controller.dto.PlayerDTO;
import com.example.vueljoy.controller.dto.UserDTO;
import com.example.vueljoy.cron.Scheduler;
import com.example.vueljoy.model.Player;
import com.example.vueljoy.model.User;
import com.example.vueljoy.repository.PlayerRepository;
import com.example.vueljoy.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    private final Scheduler scheduler;
    public String registerUser(UserDTO userDTO) {
        if (userRepository.findBySeat(userDTO.getSeat()).isPresent()) {
            return "This seat is not your seat! Check again";
        } else {
            User user =  new User(userDTO.getName(), userDTO.getSeat());
            userRepository.save(user);
            return (String.format("""
                Get Ready to start the game 
                """, user.getName()));
        }
    }

    public String welcomeUser(UserDTO userDTO) {
        return (String.format("""
                Get ready to start the game %s
                """, userDTO.getName()));
    }

    public String submitAnswer(PlayerDTO playerDTO) {
        // Check if the answer is correct
        var player = playerRepository.findBySeat(playerDTO.getSeat());
        if (playerDTO.isCorrect()) {
            int score = getScore(Integer.parseInt(playerDTO.getTime()));
            // check if the player is already in score, like for example, all in the first question
            if (player.isEmpty())
                playerRepository.save(new Player(playerDTO.getSeat(), score));
            else {
                player.get().setScore(player.get().getScore() + score);
                playerRepository.save(player.get());
            }
        } else {
            if (player.isEmpty())
                playerRepository.save(new Player(playerDTO.getSeat(), 0));
        }
        return null;
    }

    private int getScore(int time) {
        return 10000 / time;
    }

    public void start() throws JsonProcessingException, InterruptedException {
        scheduler.startGame();
    }
}
