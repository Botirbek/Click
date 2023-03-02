package com.example.psb_click.exceptions;

import com.example.psb_click.dto.basic.AppErrorDTO;
import com.example.psb_click.dto.basic.DataDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle500(RuntimeException e, WebRequest webRequest) {
        return ResponseEntity.ok
                (new DataDTO<>(
                        new AppErrorDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DataDTO<AppErrorDTO>> handle400(BadRequestException e, WebRequest webRequest) {
        return ResponseEntity.ok(
                new DataDTO<>(
                        new AppErrorDTO(
                                HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity.ok(new DataDTO<>(new AppErrorDTO(HttpStatus.BAD_REQUEST, String.valueOf(errors), request)));
    }

//    private Map<String, List<String>> getErrorsMap(List<String> errors) {
//        Map<String, List<String>> errorResponse = new HashMap<>();
//        errorResponse.put("errors", errors);
//        return errorResponse;
//    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle404(NotFoundException e,WebRequest webRequest) {
        return ResponseEntity.ok(
                new DataDTO<>(
                        new AppErrorDTO(
                                HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handleCustom(CustomException e, WebRequest webRequest) {
        return ResponseEntity.ok
                (new DataDTO<>(
                        new AppErrorDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
    }


}
