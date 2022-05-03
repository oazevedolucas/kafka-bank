package com.example.kafkaexample.service;

import com.example.kafkaexample.entities.Holder;
import com.example.kafkaexample.entities.HolderAccount;
import com.example.kafkaexample.repositories.HolderAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class holderAccountTest {

  @Autowired HolderAccountService holderAccountService;

  @MockBean HolderAccountRepository holderAccountRepository;

  @Test
  void shouldCreateAccountValid() {
    Holder holderMockDB = new Holder();
    holderMockDB.setId(UUID.randomUUID());
    holderMockDB.setCpf("50336912870");
    holderMockDB.setName("Lucas Azevedo");
    holderMockDB.setEmail("Lucas.azevedosooz@gmail.com");

    HolderAccount holderAccountMockDB = new HolderAccount.Builder("1").build();
    holderAccountMockDB.setId(UUID.randomUUID());

    Mockito.when(holderAccountRepository.save(Mockito.any(HolderAccount.class)))
        .thenReturn(holderAccountMockDB);
    Mockito.when(holderAccountRepository.count()).thenReturn(0L);

    Holder holderAccountSaved = holderAccountService.createHolderAccount(holderMockDB);
    Assertions.assertNotNull(holderAccountSaved.getHolderAccount().getId());
    Assertions.assertEquals("1", holderAccountSaved.getHolderAccount().getAccountNumber());
    Assertions.assertEquals(BigDecimal.ZERO, holderAccountSaved.getHolderAccount().getBalance());
    Assertions.assertEquals("001", holderAccountSaved.getHolderAccount().getAgencyNumber());
  }

  @Test
  void returnIntegerValidToAccountNumber() {
    Mockito.when(holderAccountRepository.count()).thenReturn(0L);

    Integer accountNumber = holderAccountService.getAccountNumber();

    Assertions.assertEquals(1, accountNumber);
  }
}
