package com.example.ProjectElearning.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandling {


        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
            System.out.println("entered handleValidationExceptions");
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
            System.out.println("entered handleResourceNotFoundException");
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("messagE", ex.getMessage());
            errorDetails.put("details", request.getDescription(false));

            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }



        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
            System.out.println("entered handleIllegalArgumentException");
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("message", ex.getMessage());
            errorDetails.put("details", request.getDescription(false));

            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex, WebRequest request) {
        System.out.println("entered handleGlobalException");
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("details", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
