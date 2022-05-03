package com.example.kafkaexample.service;

import com.example.kafkaexample.entities.Holder;
import com.example.kafkaexample.entities.HolderAccount;
import com.example.kafkaexample.repositories.HolderAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class HolderAccountService {

  @Autowired HolderAccountRepository holderAccountRepository;

  public Holder createHolderAccount(@NotNull Holder holder) {
    HolderAccount holderAccount =
        new HolderAccount.Builder(this.getAccountNumber().toString()).build();
    holderAccount = holderAccountRepository.save(holderAccount);
    holder.setHolderAccount(holderAccount);
    return holder;
  }

  public Integer getAccountNumber() {
    return (int) holderAccountRepository.count() + 1;
  }
}
