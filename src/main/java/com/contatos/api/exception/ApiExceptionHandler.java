package com.contatos.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.contatos.core.exception.CpfUniqueViolationException;
import com.contatos.core.exception.RegistroNaoEncontradoException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    
    // exceção para validação dos campos (jakarta validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result) {
        
        // mostra erro no console do spring boot (@Slf4j)
        log.error(">>> API Error: ", ex);

        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, 
                "Campo(s) inválido(s)", result)); // result é para validação de campos
    }

    // exceções para campos duplicados no banco de dados
    @ExceptionHandler(CpfUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> uniqueViolationException(
            RuntimeException ex, HttpServletRequest request) {
        
        // mostra erro no console do spring boot
        log.error(">>> API Error: ", ex);

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(
            RuntimeException ex, HttpServletRequest request) {
        
        // mostra erro no console do spring boot
        log.error(">>> API Error: ", ex);

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    // trata qualquer exceção que não estiver acima
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerErrorException(Exception ex, 
        HttpServletRequest request) {
        
        ErrorMessage error = new ErrorMessage(
            request, HttpStatus.INTERNAL_SERVER_ERROR, 
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error("Internal Server Error {} {} ", error, ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error);
    }
}
