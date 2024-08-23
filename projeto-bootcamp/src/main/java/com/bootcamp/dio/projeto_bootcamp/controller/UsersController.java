package com.bootcamp.dio.projeto_bootcamp.controller;

import com.bootcamp.dio.projeto_bootcamp.model.Users;
import com.bootcamp.dio.projeto_bootcamp.service.UsersService;
import com.bootcamp.dio.projeto_bootcamp.utils.models.ApiResponse;
import com.bootcamp.dio.projeto_bootcamp.utils.models.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<Users>>> findAll (
            @PageableDefault(size = 10)Pageable pageable) {
        ApiResponse<PaginatedData<Users>> response = new ApiResponse<>();
        PaginatedData<Users> data = service.findAll(pageable);
        response.of(
                HttpStatus.CREATED,
                "Lista de usuários",
                data);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
