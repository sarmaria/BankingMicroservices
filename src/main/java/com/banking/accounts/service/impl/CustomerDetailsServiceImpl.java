package com.banking.accounts.service.impl;

import com.banking.accounts.dto.AccountDto;
import com.banking.accounts.dto.CardDto;
import com.banking.accounts.dto.CustomerDetailsDto;
import com.banking.accounts.dto.LoanDto;
import com.banking.accounts.entity.Account;
import com.banking.accounts.entity.Customer;
import com.banking.accounts.exception.ResourceNotFoundException;
import com.banking.accounts.mapper.AccountMapper;
import com.banking.accounts.mapper.CustomerDetailsMapper;
import com.banking.accounts.repository.AccountRepository;
import com.banking.accounts.repository.CustomerRepository;
import com.banking.accounts.service.ICustomerDetailsService;
import com.banking.accounts.service.client.CardsFeignClient;
import com.banking.accounts.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    public CustomerDetailsServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository, CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }

    /**
     * @param mobileNumber
     * @return Details of the customer
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String traceId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
                );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "CustomerId", String.valueOf(customer.getCustomerId()))
                );
        AccountDto accountDto = AccountMapper.mapToDto(account);
        CardDto cardDto = Optional.ofNullable(cardsFeignClient.fetch(traceId, mobileNumber)).map(ResponseEntity::getBody).orElse(null);
        LoanDto loanDto = Optional.ofNullable(loansFeignClient.fetch(traceId, mobileNumber)).map(ResponseEntity::getBody).orElse(null);
        return CustomerDetailsMapper.toDto(customer, accountDto, cardDto, loanDto);
    }
}
