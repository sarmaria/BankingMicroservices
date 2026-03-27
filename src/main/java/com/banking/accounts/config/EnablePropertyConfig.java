package com.banking.accounts.config;

import com.banking.accounts.dto.AccountsContactInfoDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AccountsContactInfoDto.class)
public class EnablePropertyConfig {
}
