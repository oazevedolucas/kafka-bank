package com.example.kafkaexample.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "holder_account")
@NoArgsConstructor
@AllArgsConstructor
public class HolderAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "holder_account_id")
  private UUID id;

  @Column(name = "number")
  private String accountNumber;

  @Column(name = "agency_number")
  private String agencyNumber;

  @Column(name = "balance")
  private BigDecimal balance;

  public static class Builder {

    private final String accountNumber;
    private final String agencyNumber;
    private final BigDecimal balance;

    public Builder(String accountNumber) {
      this.agencyNumber = "001";
      this.accountNumber = accountNumber;
      this.balance = BigDecimal.ZERO;
    }

    public HolderAccount build() {
      return new HolderAccount(this);
    }
  }

  private HolderAccount(Builder builder) {
    accountNumber = builder.accountNumber;
    agencyNumber = builder.agencyNumber;
    balance = builder.balance;
  }
}
