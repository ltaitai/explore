package com.explore.go.controller;

import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(topics = "event.create.user", groupId = "group-id")
public class KafkaListnerController {
}
