package com.tuservicio.application.config;

import com.tuservicio.core.port.ConciliationUseCase;
import com.tuservicio.core.port.TransactionRepositoryPort;
import com.tuservicio.core.service.ConciliationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConciliationUseCase conciliationUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new ConciliationService(transactionRepositoryPort);
    }
}
