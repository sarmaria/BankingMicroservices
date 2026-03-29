package com.banking.accounts.service;

import com.banking.accounts.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {
    /**
     *
     * @param mobileNumber
     * @return Details of the customer
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
