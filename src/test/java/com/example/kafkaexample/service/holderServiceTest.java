package com.example.kafkaexample.service;

import com.example.kafkaexample.entities.Holder;
import com.example.kafkaexample.entities.HolderAccount;
import com.example.kafkaexample.repositories.HolderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class holderServiceTest {

  @Autowired HolderService holderService;

  @MockBean HolderRepository holderRepository;

  @Test
  void shouldCreateValidHolder() {
    Holder holderToSave = new Holder();
    holderToSave.setCpf("50336912870");
    holderToSave.setName("Lucas Azevedo");
    holderToSave.setEmail("Lucas.azevedosooz@gmail.com");

    Holder holderMockDB = new Holder();
    holderMockDB.setId(UUID.randomUUID());
    holderMockDB.setCpf("50336912870");
    holderMockDB.setName("Lucas Azevedo");
    holderMockDB.setEmail("Lucas.azevedosooz@gmail.com");
    holderMockDB.setHolderAccount(new HolderAccount.Builder("1").build());

    Mockito.when(holderRepository.save(Mockito.any(Holder.class))).thenReturn(holderMockDB);
    Holder holderSaved = holderService.saveHolder(holderToSave);

    Mockito.verify(holderRepository).save(Mockito.any());
    Assertions.assertNotNull(holderSaved.getId());
    Assertions.assertEquals("Lucas Azevedo", holderSaved.getName());
    Assertions.assertEquals("50336912870", holderSaved.getCpf());
    Assertions.assertEquals("Lucas.azevedosooz@gmail.com", holderSaved.getEmail());
    Assertions.assertEquals("1", holderSaved.getHolderAccount().getAccountNumber());
    Assertions.assertEquals("001", holderSaved.getHolderAccount().getAgencyNumber());
    Assertions.assertEquals(BigDecimal.ZERO, holderSaved.getHolderAccount().getBalance());
  }

  @Test
  void shouldNotCreateHolderInvalid() {
    Holder holderToSave = new Holder();

    Assertions.assertThrows(
        ConstraintViolationException.class, () -> holderService.saveHolder(holderToSave));
    Mockito.verify(holderRepository, Mockito.never()).save(Mockito.any());
  }

  @Test
  void shouldListAllHoldersCreates() {

    List<Holder> holderListMockDB = new ArrayList<>();

    holderListMockDB.add(new Holder());
    holderListMockDB.add(new Holder());
    holderListMockDB.add(new Holder());

    Mockito.when(holderRepository.findAll()).thenReturn(holderListMockDB);

    List<Holder> holderList = holderService.listAllHolders();
    Assertions.assertEquals(3, holderList.size());
  }
}
