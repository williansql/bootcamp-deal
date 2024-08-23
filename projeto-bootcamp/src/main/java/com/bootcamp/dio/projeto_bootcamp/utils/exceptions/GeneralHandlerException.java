package com.bootcamp.dio.projeto_bootcamp.utils.exceptions;

import com.bootcamp.dio.projeto_bootcamp.utils.models.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralHandlerException {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.of(e.returnStatusCode(), e.getMessage());
        return ResponseEntity.status(e.returnStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.of(e.returnStatusCode(), e.getMessage());
        return ResponseEntity.status(e.returnStatusCode()).body(apiResponse);
    }


}