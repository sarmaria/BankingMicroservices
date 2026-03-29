package com.banking.accounts.mapper;

import com.banking.accounts.dto.AccountDto;
import com.banking.accounts.dto.CardDto;
import com.banking.accounts.dto.CustomerDetailsDto;
import com.banking.accounts.dto.LoanDto;
import com.banking.accounts.entity.Customer;

public class CustomerDetailsMapper {
    public static CustomerDetailsDto toDto (Customer customer, AccountDto accountDto, CardDto cardDto, LoanDto loanDto) {
        return new CustomerDetailsDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                accountDto,
                cardDto,
                loanDto
        );
    }
}
