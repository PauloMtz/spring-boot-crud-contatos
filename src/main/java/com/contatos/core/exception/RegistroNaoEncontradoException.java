package com.contatos.core.exception;

import jakarta.persistence.EntityNotFoundException;

public class RegistroNaoEncontradoException extends EntityNotFoundException {
    
    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}
