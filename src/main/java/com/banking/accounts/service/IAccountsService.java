package com.banking.accounts.service;

import com.banking.accounts.dto.CustomerDto;

public interface IAccountsService {
    /**
     * Create an account based on customer details
     * @param customerDto
     */
    void createNewAccount(CustomerDto customerDto);

    /**
     * Fetch account details based on the customer mobile number
     * @param mobileNumber
     * @return Customer details
     */
    CustomerDto getAccountDetails(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return True if update is successfully
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return True if update is successfully
     */
    boolean deleteAccount(String mobileNumber);
}
