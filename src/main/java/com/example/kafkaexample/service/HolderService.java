package com.example.kafkaexample.service;

import com.example.kafkaexample.entities.Holder;
import com.example.kafkaexample.repositories.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class HolderService {

  @Autowired private HolderRepository holderRepository;

  public Holder saveHolder(@Valid Holder holder) {
    return holderRepository.save(holder);
  }

  public List<Holder> listAllHolders() {
    return holderRepository.findAll();
  }
}
