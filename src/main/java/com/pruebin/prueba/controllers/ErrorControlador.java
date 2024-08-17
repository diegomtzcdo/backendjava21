package com.pruebin.prueba.controllers;

import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.dto.DetallesErrorDTO;
import com.pruebin.prueba.excepciones.AppException;
import com.pruebin.prueba.excepciones.BadRequestException;
import com.pruebin.prueba.excepciones.OperacionNoPermitidaExcepcion;
import com.pruebin.prueba.excepciones.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorControlador extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class, AppException.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(RuntimeException e, WebRequest request) {
        return CustomResponseEntity.failure(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "Error en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(ResourceNotFoundException e, WebRequest request) {
        return CustomResponseEntity.failure(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "No encontrado.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(BadRequestException e, WebRequest request) {
        return CustomResponseEntity.failure(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "Error de formato.", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({OperacionNoPermitidaExcepcion.class})
    public CustomResponseEntity<DetallesErrorDTO> handle(OperacionNoPermitidaExcepcion e, WebRequest request) {
        return CustomResponseEntity.failure(DetallesErrorDTO.builder().timestamp(new Date()).message(e.getMessage()).
                details(request.getDescription(true)).build(), "No Autorizado.", HttpStatus.UNAUTHORIZED);
    }

}