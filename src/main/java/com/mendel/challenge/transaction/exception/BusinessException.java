package com.mendel.challenge.transaction.exception;

/**
 * Representa los errores de validacion del negocio
 */
public class BusinessException extends Exception {

    private String field;

    public BusinessException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
