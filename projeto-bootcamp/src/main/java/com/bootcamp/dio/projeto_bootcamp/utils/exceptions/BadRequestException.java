package com.bootcamp.dio.projeto_bootcamp.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(String message, Throwable cause){
        super(message, cause);
    }

    public HttpStatus returnStatusCode(){
        return HttpStatus.BAD_REQUEST;
    }
}