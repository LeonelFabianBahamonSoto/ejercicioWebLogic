package com.example.ejercicioWebLogic.exceptions;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Captura CUALQUIER excepción que herede de ApiException
    // (ResourceNotFoundException, BadRequestException, futuras nuevas, etc.)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(
            ApiException ex, WebRequest request) {

        HttpStatus status = Optional.ofNullable(ex.getStatus())
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        String error = Optional.ofNullable(ex.getError())
                .orElse("Internal Server Error");

        String message = Optional.ofNullable(ex.getMessage())
                .orElse("Ocurrió un error inesperado. Contacte al administrador.");

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                error,
                message,
                request.getDescription(false)
        );

        return ResponseEntity.status(status).body(response);
    }

    // Fallback para CUALQUIER excepción no controlada que no sea ApiException
    // (NullPointerException, errores de BD, etc. que no anticipaste)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(
            Exception ex, WebRequest request) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ocurrió un error inesperado. Contacte al administrador.",
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
