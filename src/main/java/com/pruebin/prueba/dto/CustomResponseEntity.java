package com.pruebin.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CustomResponseEntity<T> extends ResponseEntity<CustomResponseEntity.ApiResponse<T>> {

    public CustomResponseEntity(boolean success, T data, String message, HttpStatus status) {
        super(new ApiResponse<>(success, data, message), status);
    }

    @Data
    @AllArgsConstructor
    public static class ApiResponse<T> {
        private boolean success;
        private T data;
        private String message;
    }

    public static <T> CustomResponseEntity<T> customStatus(T data, String message, HttpStatus status) {
        return new CustomResponseEntity<>(true, data, message, status);
    }

    public static <T> CustomResponseEntity<T> success200(T data, String message) {
        return new CustomResponseEntity<>(true, data, message, HttpStatus.OK);
    }

    public static <T> CustomResponseEntity<T> failure(T data, String message, HttpStatus status) {
        return new CustomResponseEntity<>(false, null, message, status);
    }
}