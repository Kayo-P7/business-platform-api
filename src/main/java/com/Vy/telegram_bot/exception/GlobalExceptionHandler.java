package com.Vy.telegram_bot.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // essa classe vai observar todos os controllers da aplicação e tratar situações específicas
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> productNotFound(
            ProductNotFoundException ex, HttpServletRequest request
    ) {
        StandardError error = new StandardError
                (
                        Instant.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Resource not found",
                        ex.getMessage(),
                        request.getRequestURI() //caminho que foi solicitado
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<StandardError> InsufficientSock
            (
                    InsufficientStockException ex, HttpServletRequest request
            ) {
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Stock Insufficient",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<StandardError> ProductAlreadyExists
            (ProductAlreadyExistsException ex, HttpServletRequest request) {
        StandardError error = new StandardError
                (
                        Instant.now(),
                        HttpStatus.CONFLICT.value(),
                        "Product Already Exist",
                        ex.getMessage(),
                        request.getRequestURI()
                );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolationException
            (
                    DataIntegrityViolationException ex, HttpServletRequest request
            ) {
        StandardError error = new StandardError
                (
                        Instant.now(),
                        HttpStatus.CONFLICT.value(),
                        "Data integrity violation",
                        "Não é possível realizar essa operação porque o recurso está sendo utilizado.",
                        request.getRequestURI()
                );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> MethodArgumentNotValidException
            (
                    MethodArgumentNotValidException ex, HttpServletRequest request
            ) {
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                "Os dados enviados são inválidos.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
