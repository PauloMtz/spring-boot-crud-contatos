package com.contatos.core.exception;

public class CpfUniqueViolationException extends RuntimeException {
    
    public CpfUniqueViolationException(String message) {
        super(message);
    }
}
