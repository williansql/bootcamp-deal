package com.bootcamp.dio.projeto_bootcamp.utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status = HttpStatus.OK.value();
    private String message = "";
    private T data = null;

    public ApiResponse<T> of(T data) {
        this.data = data;
        return this;
    }

    public ApiResponse<T> of(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.message = message;
        return this;
    }

    public ApiResponse<T> of(HttpStatus httpStatus, T data) {
        this.status = httpStatus.value();
        this.data = data;
        return this;
    }

    public ApiResponse<T> of(String message, T data) {
        this.message = message;
        this.data = data;
        return this;
    }

    public ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.value();
        this.data = data;
        this.message = message;
        return this;
    }
}
