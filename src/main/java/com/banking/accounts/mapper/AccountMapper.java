package com.banking.accounts.mapper;

import com.banking.accounts.dto.AccountDto;
import com.banking.accounts.entity.Account;

public class AccountMapper {

    public static Account mapToEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountId(accountDto.accountNumber());
        account.setAccountType(accountDto.accountType());
        account.setBranchAddress(accountDto.branchAddress());
        account.setCustomerId(accountDto.customerId());
        return account;
    }

    public static AccountDto mapToDto(Account account) {
        return new AccountDto(account.getAccountId(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getBranchAddress());
    }
}
