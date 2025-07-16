package com.david.fintrack.dto;

public class ErrorResponse {

    // clase Error personalizada para manejar errores
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
