package com.banking.accounts.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.banking.accounts.service.client")
public class FeignClientConfig {
}
