package com.example.kafkaexample.consumer;

import com.example.kafkaexample.entities.Holder;
import com.example.kafkaexample.producer.HolderProducer;
import com.example.kafkaexample.service.HolderAccountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class HolderAccountConsumer {
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Autowired HolderAccountService holderAccountService;

  @Autowired
  HolderProducer holderProducer;

  @KafkaListener(topics = "newHolderAccountQueue", groupId = "soo")
  public void consume(String message) {
    log.info("Consume New Holder Account Queue, value : {}", message);

    Holder holder = gson.fromJson(message, Holder.class);
    holder = holderAccountService.createHolderAccount(holder);
    holderProducer.updateHolderMessage(holder);
  }
}
