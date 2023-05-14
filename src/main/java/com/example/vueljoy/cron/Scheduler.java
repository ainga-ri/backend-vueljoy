package com.example.vueljoy.cron;

import com.example.vueljoy.model.Player;
import com.example.vueljoy.model.Question;
import com.example.vueljoy.repository.PlayerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class Scheduler {
    private final SimpMessagingTemplate template;
    private final PlayerRepository playerRepository;
    @Value("1")
    private int questionNo;
//    @Scheduled(fixedDelay = 100000, initialDelay = 200)
//    //This class may not be necessary
//    public void StartGameToRegister() {
//        this.template.convertAndSend("/topic/broadcast");
//        long now = System.currentTimeMillis() / 1000;
//        System.out.println("SCHEDULE game task USING CRON JOBS " + now);
//    }

    @Scheduled(fixedDelay = 15000, initialDelay = 35000)
    // Initial delay to register once we wun, after that, run the question every 10 seconds
    public void scheduleQuestion() throws JsonProcessingException {
        //var questions = getQuestionsJSON(questionNo);
        ObjectMapper mapper = new ObjectMapper();
        Question question = new Question(questionNo);
        String json = mapper.writeValueAsString(question);

        this.template.convertAndSend("/topic/broadcast", json);

        long now = System.currentTimeMillis() / 1000;
        System.out.println("SCHEDULE question USING CRON JOBS " + now);
        questionNo++;
        log.error(String.valueOf(questionNo));
    }

    public void startGame() throws JsonProcessingException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(10000);

            ObjectMapper mapper = new ObjectMapper();
            Question question = new Question(questionNo);
            String json = mapper.writeValueAsString(question);

            this.template.convertAndSend("/topic/broadcast", json);

            long now = System.currentTimeMillis() / 1000;
            System.out.println("SCHEDULE question USING CRON JOBS " + now);
            questionNo++;
            log.error(String.valueOf(questionNo));
        }
    }


    @Scheduled(fixedDelay = 15000, initialDelay = 30000)
    // Initial delay to register once we wun, after that, run the question every 10 seconds
    public void scheduleRanking() throws JsonProcessingException {
        //var ranking = playerRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Player> players = playerRepository.findAll();
        Map<Integer, Player> rankingMap = new HashMap<>();

        for (int i = 0; i < players.size(); i++) {
            rankingMap.put(i+1, players.get(i));
        }

        Map<String, Object> rankingObj = new HashMap<>();
        rankingObj.put("type", "r");
        rankingObj.put("r", rankingMap);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String rankingJson = gson.toJson(rankingObj);

        System.out.println(rankingJson);
        this.template.convertAndSend("/topic/broadcast", rankingJson);

        long now = System.currentTimeMillis() / 1000;
        System.out.println("SCHEDULE question USING CRON JOBS " + now);
    }

}
