package com.example.kafkaexample.controllers;

import com.example.kafkaexample.entities.Holder;
import com.example.kafkaexample.producer.HolderProducer;
import com.example.kafkaexample.service.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

  @Autowired HolderProducer holderProducer;

  @Autowired HolderService holderService;

  @PostMapping("/create-account")
  public ResponseEntity<Void> publish(@Validated @RequestBody Holder holder) {
    holderProducer.createHolderMessage(holder);
    return ResponseEntity.status(202).build();
  }

  @GetMapping("/list-all-holders")
  public ResponseEntity<List<Holder>> publish() {
    return ResponseEntity.status(200).body(holderService.listAllHolders());
  }
}
