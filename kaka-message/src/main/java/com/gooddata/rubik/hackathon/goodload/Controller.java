package com.gooddata.rubik.hackathon.goodload;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooddata.rubik.hackathon.goodload.common.Opportunity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping(path = "/send/opportunity/")
    public void sendOpportunity(@RequestBody final Opportunity opportunity) {
        logger.info("Start to process: " + opportunity.getName());
        try {
            this.template.send("rubik-hack.input", objectMapper.writeValueAsString(opportunity));
        } catch (JsonProcessingException e) {
            logger.warn("Error while write to json", e);
        }
    }

}


