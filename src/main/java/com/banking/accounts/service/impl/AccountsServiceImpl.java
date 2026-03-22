package com.banking.accounts.service.impl;

import com.banking.accounts.constants.AccountType;
import com.banking.accounts.dto.AccountDto;
import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.entity.Account;
import com.banking.accounts.entity.Customer;
import com.banking.accounts.exception.CustomerAlreadyExistsException;
import com.banking.accounts.exception.ResourceNotFoundException;
import com.banking.accounts.mapper.AccountMapper;
import com.banking.accounts.mapper.CustomerMapper;
import com.banking.accounts.repository.AccountRepository;
import com.banking.accounts.repository.CustomerRepository;
import com.banking.accounts.service.IAccountsService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class AccountsServiceImpl implements IAccountsService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     * Create an account based on customer details
     *
     * @param customerDto
     */
    @Override
    public void createNewAccount(CustomerDto customerDto) {
        Customer createdCustomer = createCustomer(customerDto);
        createAccountForCustomer(createdCustomer);
    }

    /**
     * Fetch account details based on the customer mobile number
     *
     * @param mobileNumber
     * @return Customer details
     */
    @Override
    public CustomerDto getAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
                );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "CustomerId", String.valueOf(customer.getCustomerId()))
                );
        return CustomerMapper.toDto(customer, AccountMapper.mapToDto(account));
    }

    /**
     * @param customerDto
     * @return True if update is successfully
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        final AccountDto accountDto = customerDto.accountDto();
        if (accountDto != null) {
            final Account account = accountRepository.findById(accountDto.accountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", String.valueOf(accountDto.accountNumber()))
            );
            final Account updatedAccount = accountRepository.save(AccountMapper.mapToEntity(accountDto));
            final Long customerId = updatedAccount.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", String.valueOf(customerId))
            );
            CustomerMapper.toEntity(customer, customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return True if update is successfully
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Customer createCustomer(CustomerDto customerDto) {
        final Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer Already Exists.");
        }
        final Customer customer = CustomerMapper.toEntity(new Customer(), customerDto);
        return customerRepository.save(customer);
    }

    private void createAccountForCustomer(Customer customer) {
        final Account account = new Account();
        final long accountId = 1000000000L + new Random().nextLong(1000000000);
        account.setAccountId(accountId);
        account.setCustomerId(customer.getCustomerId());
        account.setAccountType(String.valueOf(AccountType.SAVINGS));
        account.setMobileNumber(customer.getMobileNumber());
        account.setBranchAddress("Triplicane");
        accountRepository.save(account);
    }
}
