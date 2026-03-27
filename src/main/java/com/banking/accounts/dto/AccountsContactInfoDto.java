package com.banking.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = AccountsContactInfoDto.PREFIX)
public class AccountsContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
    static final String PREFIX = "accounts";
}
