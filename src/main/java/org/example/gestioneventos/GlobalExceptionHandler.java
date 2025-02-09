package org.example.gestioneventos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    //Para que salgan los mensajes de @Param
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Para que salgan los mensajes de excepciones en ServicioCompra
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        // Crear un mapa para incluir el mensaje y el status
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getReason()); // Tomamos el mensaje de la excepción
        errors.put("status", ex.getStatusCode().toString()); // El código de estado HTTP (ej: "400 BAD_REQUEST")

        return new ResponseEntity<>(errors, ex.getStatusCode());
    }
}
