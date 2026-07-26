package com.example.ejercicioWebLogic.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final String error;

    // Constructor completo
    public ApiException(HttpStatus status, String error, String message) {
        super(message);
        this.status = (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        this.error = (error != null && !error.isBlank()) ? error : "Internal Server Error";
    }

    // Solo mensaje -> status y error quedan con default (500)
    public ApiException(String message) {
        this(null, null, message);
    }

    // Sin nada -> mensaje también con default
    public ApiException() {
        this(null, null, "Ocurrió un error inesperado. Contacte al administrador.");
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
