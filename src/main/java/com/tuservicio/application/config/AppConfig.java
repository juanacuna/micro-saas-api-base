package com.tuservicio.application.config;

import com.tuservicio.core.port.ConciliationUseCase;
import com.tuservicio.core.port.TransactionRepositoryPort;
import com.tuservicio.core.service.ConciliationService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
// 1. Escanea los adaptadores (ej. OracleDatabaseAdapter, S3StorageAdapter, RestControllers)
@ComponentScan(basePackages = "com.tuservicio.infrastructure.adapter")
// 2. Habilita y localiza las interfaces de Spring Data JPA
@EnableJpaRepositories(basePackages = "com.tuservicio.infrastructure.repository")
// 3. Localiza las entidades JPA (@Entity) para que Hibernate las registre
@EntityScan(basePackages = "com.tuservicio.infrastructure.entity")
public class AppConfig {

    @Bean
    public ConciliationUseCase conciliationUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new ConciliationService(transactionRepositoryPort);
    }
}
