package com.example.kafkaexample.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "holder")
@ToString
public class Holder {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "holder_id")
  private UUID id;

  @Column(name = "holder_name")
  @NotBlank
  private String name;

  @Column(name = "cpf")
  @CPF
  private String cpf;

  @Email
  @Column(name = "email")
  private String email;

  @ManyToOne
  @JoinColumn(name = "holder_account_id")
  private HolderAccount holderAccount;

}
