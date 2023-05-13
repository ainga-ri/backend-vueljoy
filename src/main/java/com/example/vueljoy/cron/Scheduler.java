package com.example.vueljoy.cron;

import com.example.vueljoy.controller.WebSocketsController;
import com.example.vueljoy.model.send.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class Scheduler {
//    private final WebSocketsController webSocketsController;
    @Autowired
    private SimpMessagingTemplate template;

//    @Value("${s:0}")
    @Value("0")
    private int questionNo;
    @Scheduled(fixedDelay = 100000, initialDelay = 200)
    public void scheduleTaskUsingCron() {
        this.template.convertAndSend("/topic/broadcast", "FIRE");
        //webSocketsController.question();
        long now = System.currentTimeMillis() / 1000;
        System.out.println("SCHEDULE game task USING CRON JOBS " + now);
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 20000)
//    @Scheduled(cron = "10 * * * * *")
    public void scheduleQuestion() throws JsonProcessingException {

//        repo.findAll
//
//                questions[questionNo]
      log.error("tester");
        ObjectMapper mapper = new ObjectMapper();
        Question question = new Question("wustsdifjasdf", new String[]{"2", "3", "4", "5"});
        String json = mapper.writeValueAsString( question );

//        this.template.convertAndSend("/topic/broadcast", "{\"q\": \"patata\"}");
        this.template.convertAndSend("/topic/broadcast", json);
        //webSocketsController.question();
        long now = System.currentTimeMillis() / 1000;
        System.out.println("SCHEDULE question USING CRON JOBS " + now);



//        if (questionNo == 16 )
//        {
//         show  finalscore
//        }
//
        questionNo++;
        log.error("question is" + questionNo);
    }
}
