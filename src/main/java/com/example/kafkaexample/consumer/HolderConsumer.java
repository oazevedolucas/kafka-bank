package com.example.kafkaexample.consumer;

import com.example.kafkaexample.entities.Holder;
import com.example.kafkaexample.producer.HolderAccountProducer;
import com.example.kafkaexample.service.HolderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class HolderConsumer {

  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Autowired HolderService holderService;

  @Autowired HolderAccountProducer holderAccountProducer;

  @KafkaListener(topics = "newHolderQueue", groupId = "soo")
  public void consumeNewHolderQueue(String message) {
    log.info("Consume New Holder Queue, value : {}", message);
    Holder holder = gson.fromJson(message, Holder.class);
    holder = holderService.saveHolder(holder);
    holderAccountProducer.createHolderAccountMessage(holder);
  }

  @KafkaListener(topics = "updateHolderQueue", groupId = "soo")
  public void consumeUpdateHolderQueue(String message) {
    log.info("Consume Update Holder Queue, value : {}", message);
    Holder holder = gson.fromJson(message, Holder.class);
    holderService.saveHolder(holder);
  }
}
