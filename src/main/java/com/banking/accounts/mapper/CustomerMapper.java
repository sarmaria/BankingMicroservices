package com.banking.accounts.mapper;

import com.banking.accounts.dto.AccountDto;
import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.entity.Customer;

public class CustomerMapper {
    public static Customer toEntity(Customer customer, CustomerDto customerDto){
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setMobileNumber(customerDto.mobileNumber());
        return customer;
    }

    public static CustomerDto toDto(Customer customer, AccountDto accountDto){
      return new CustomerDto(customer.getName(), customer.getEmail(), customer.getMobileNumber(), accountDto);
    }
}
