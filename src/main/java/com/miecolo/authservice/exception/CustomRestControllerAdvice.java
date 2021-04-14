package com.miecolo.authservice.exception;

import com.miecolo.authservice.dto.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {

    @ExceptionHandler(UsernameOrEmailExistException.class)
    public ResponseEntity<?> userNameOrEmailExistHandler(UsernameOrEmailExistException usernameOrEmailExistException){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDTO(usernameOrEmailExistException.getMessage(),
                                "UsernameOrEmailExistException",
                                400));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedHandler(UnauthorizedException unauthorizedException){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDTO(unauthorizedException.getMessage(),
                        "UnauthorizedException",
                        401));
    }

    @ExceptionHandler(BadIdException.class)
    public ResponseEntity<?> badIdExceptionHandler(BadIdException badIdException){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDTO(badIdException.getMessage(),
                        "BadIdException",
                        400));
    }
}
