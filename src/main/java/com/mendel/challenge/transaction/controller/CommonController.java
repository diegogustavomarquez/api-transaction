package com.mendel.challenge.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller que contiene logica comun a muchos controller.
 */
public abstract class CommonController {

    /**
     * list of error from validate from entity
     *
     * @param result
     * @return
     */
    public ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    public ResponseEntity<?> validate(String field, String message) {
        Map<String, Object> errores = new HashMap<>();
        errores.put(field, message);
        return ResponseEntity.badRequest().body(errores);
    }

}
