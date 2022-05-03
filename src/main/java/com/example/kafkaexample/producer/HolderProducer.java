package com.example.kafkaexample.producer;

import com.example.kafkaexample.entities.Holder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class HolderProducer {

  @Autowired private KafkaTemplate<String, Holder> kafkaTemplate;

  public void createHolderMessage(Holder holder) {
    kafkaTemplate.send("newHolderQueue", holder);
    log.info("Send Message to New Holder Queue : {}", holder.toString());
  }

  public void updateHolderMessage(Holder holder) {
    kafkaTemplate.send("updateHolderQueue", holder);
    log.info("Send Message to Update Holder Queue : {}", holder.toString());
  }
}
