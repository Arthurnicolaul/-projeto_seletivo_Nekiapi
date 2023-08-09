package com.seletivo.projeto.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ApiError errorResponse = new ApiError(status,
                "Existem campos inválidos. Confira o preenchimento", errors);

        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }


    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("Algum campo não está sendo reconhecido.");

        ApiError errorResponse = new ApiError(status,
                "Existem campos inválidos. Confira o preenchimento", errors);

        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<Object> handleUsuarioException(UsuarioException usuarioException) {
        return ResponseEntity.unprocessableEntity().body(usuarioException.getMessage());
    }

    @ExceptionHandler(SkillException.class)
    public ResponseEntity<Object> handleSkillException(SkillException skillException) {
        return ResponseEntity.unprocessableEntity().body(skillException.getMessage());
    }
}