package com.example.kafkaexample.producer;

import com.example.kafkaexample.entities.Holder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class HolderAccountProducer {

  @Autowired private KafkaTemplate<String, Holder> kafkaTemplate;

  public void createHolderAccountMessage(Holder holder) {
    kafkaTemplate.send("newHolderAccountQueue", holder);
    log.info("Send Message to New Holder Account Queue : {}", holder.toString() );
  }
}
