package com.example.ejercicioWebLogic.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, "Not Found", message);
    }

    // Ejemplo de conveniencia para tu caso de "cliente no existe"
    public ResourceNotFoundException(String entidad, Long id) {
        super(HttpStatus.NOT_FOUND, "Not Found",
                "El " + entidad + " con id: " + id + " no existe.");
    }
}
