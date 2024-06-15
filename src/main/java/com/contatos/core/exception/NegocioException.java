package com.contatos.core.exception;

import org.springframework.validation.FieldError;

public class NegocioException extends ValidacaoException {
    
    public NegocioException(String message, FieldError fieldError) {
        super(message, fieldError);
    }
}
