package com.tuservicio.infrastructure.adapter;

import com.tuservicio.core.domain.Transaction;
import com.tuservicio.core.port.TransactionRepositoryPort;
import com.tuservicio.infrastructure.entity.TransactionEntity;
import com.tuservicio.infrastructure.repository.JpaTransactionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OracleDatabaseAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository jpaTransactionRepository;

    public OracleDatabaseAdapter(JpaTransactionRepository jpaTransactionRepository) {
        this.jpaTransactionRepository = jpaTransactionRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = mapToEntity(transaction);
        TransactionEntity saved = jpaTransactionRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return jpaTransactionRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Transaction> findAll() {
        return jpaTransactionRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private TransactionEntity mapToEntity(Transaction domain) {
        return new TransactionEntity(
            domain.getId(),
            domain.getReference(),
            domain.getAmount(),
            domain.getTimestamp(),
            domain.getStatus()
        );
    }

    private Transaction mapToDomain(TransactionEntity entity) {
        return new Transaction(
            entity.getId(),
            entity.getReference(),
            entity.getAmount(),
            entity.getTimestamp(),
            entity.getStatus()
        );
    }
}
