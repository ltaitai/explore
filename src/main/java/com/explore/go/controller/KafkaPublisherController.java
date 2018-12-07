package com.explore.go.controller;

import com.explore.go.utils.KafkaProducer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class KafkaPublisherController {

    Logger log = LogManager.getLogger(KafkaPublisherController.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @RequestMapping(value="Publish Message",method = RequestMethod.POST)
    public ResponseEntity<String> publish(@RequestParam String topic, @RequestParam String msg){
        log.debug("topic {}, msg {} ", topic, msg);
        kafkaTemplate.send(topic,msg);

        return ResponseEntity.ok("Message is Published to Topic : " + topic);
    }
}
