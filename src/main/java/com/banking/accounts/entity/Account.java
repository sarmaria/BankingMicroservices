package com.banking.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account extends BaseEntity {

    @Id
    private Long accountId;
    private Long customerId;
    private String accountType;
    private String branchAddress;
    private String mobileNumber;
}
