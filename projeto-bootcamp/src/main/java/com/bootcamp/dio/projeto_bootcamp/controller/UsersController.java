package com.bootcamp.dio.projeto_bootcamp.controller;

import com.bootcamp.dio.projeto_bootcamp.dto.UsersDTO;
import com.bootcamp.dio.projeto_bootcamp.model.Users;
import com.bootcamp.dio.projeto_bootcamp.service.UsersService;
import com.bootcamp.dio.projeto_bootcamp.utils.models.ApiResponse;
import com.bootcamp.dio.projeto_bootcamp.utils.models.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ApiResponse<Users>> create (@RequestBody UsersDTO usersDTO){
        ApiResponse<Users> response = new ApiResponse<>();
        var users = new Users();
        BeanUtils.copyProperties(usersDTO, users);
        Users data = service.create(users);
        response.of(
                HttpStatus.CREATED,
                "Usuário criado com sucesso, " + data.getLogin().toLowerCase(),
                data);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Users>> findById(@PathVariable Long id){
        ApiResponse<Users> response = new ApiResponse<>();
        Users data = service.findById(id);
        response.of(
                HttpStatus.FOUND,
                "Usuário encontrado. " + data.getLogin().toUpperCase(),
                data);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Users>> editUser(@PathVariable Long id, @RequestBody UsersDTO usersDTO){
        ApiResponse<Users> response = new ApiResponse<>();
        var users = new Users();
        BeanUtils.copyProperties(usersDTO, users);
        Users data = service.put(id, users);
        response.of(
                HttpStatus.OK,
                "Usuário editado. " + data.getLogin().toUpperCase(),
                data);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Users>> deleteUser(@PathVariable Long id ){
        ApiResponse<Users> response = new ApiResponse<>();
        service.deleteById(id);
        response.of(
                HttpStatus.OK,
                "Usuário deletado.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}