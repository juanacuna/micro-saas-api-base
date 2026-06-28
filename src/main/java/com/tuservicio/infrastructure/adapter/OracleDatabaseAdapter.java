package com.tuservicio.infrastructure.adapter;

import com.tuservicio.core.domain.Transaction;
import com.tuservicio.core.port.TransactionRepositoryPort;
import com.tuservicio.infrastructure.entity.TransactionEntity;
import com.tuservicio.infrastructure.exception.DatabaseOperationException;
import com.tuservicio.infrastructure.repository.JpaTransactionRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OracleDatabaseAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository jpaTransactionRepository;

    public OracleDatabaseAdapter(JpaTransactionRepository jpaTransactionRepository) {
        this.jpaTransactionRepository = jpaTransactionRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        try {
            TransactionEntity entity = mapToEntity(transaction);
            TransactionEntity saved = jpaTransactionRepository.save(entity);
            return mapToDomain(saved);
        } catch (DataAccessException ex) {
            throw new DatabaseOperationException("Error al persistir la transacción con ID: " + transaction.getId(), ex);
        }
    }

    @Override
    public Optional<Transaction> findById(String id) {
        try {
            return jpaTransactionRepository.findById(id).map(this::mapToDomain);
        } catch (DataAccessException ex) {
            throw new DatabaseOperationException("Error al consultar la transacción con ID: " + id, ex);
        }
    }

    @Override
    public List<Transaction> findAll() {
        try {
            return jpaTransactionRepository.findAll().stream()
                    .map(this::mapToDomain)
                    .toList(); // Optimizado para Java 16+
        } catch (DataAccessException ex) {
            throw new DatabaseOperationException("Error al recuperar el listado de transacciones", ex);
        }
    }

    // Nota de Arquitectura: En proyectos más grandes, estos métodos privados
    // deberían extraerse a un TransactionMapper dedicado para respetar el Principio de Responsabilidad Única.
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