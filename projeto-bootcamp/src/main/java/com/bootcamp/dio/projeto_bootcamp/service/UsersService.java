package com.bootcamp.dio.projeto_bootcamp.service;

import com.bootcamp.dio.projeto_bootcamp.model.Users;
import com.bootcamp.dio.projeto_bootcamp.repository.UserRepository;
import com.bootcamp.dio.projeto_bootcamp.utils.exceptions.BadRequestException;
import com.bootcamp.dio.projeto_bootcamp.utils.exceptions.NotFoundException;
import com.bootcamp.dio.projeto_bootcamp.utils.models.PaginatedData;
import com.bootcamp.dio.projeto_bootcamp.utils.models.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository repository;

    public PaginatedData<Users> findAll(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "login"));
        Page<Users> page = repository.findAll(pageable);
        return new PaginatedData<>(page.getContent(), Pagination.from(page, pageable));
    }

    public Users create(Users users) {

        Optional<Users> findSameLogin = repository.findByLoginIgnoreCase(users.getLogin());
        if (findSameLogin.isPresent()){
            throw new BadRequestException("Já existe um usuário com esse login.");
        }
        return repository.save(users);
    }

    public Users findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de usuário não encontrado."));
    }

    public Users put(Long id, Users users) {
        Users existingUser = findById(id);
        users.setId(existingUser.getId());
        return repository.save(users);
    }

    public void deleteById(Long id) {
        Users existingId = findById(id);
        repository.deleteById(existingId.getId());
    }
}