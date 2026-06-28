package com.tuservicio.infrastructure.exception;

/**
 * Excepción de infraestructura diseñada para enmascarar errores técnicos de la base de datos
 * y evitar que las excepciones específicas del framework (como DataAccessException)
 * contaminen el dominio.
 */
public class DatabaseOperationException extends RuntimeException {

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}