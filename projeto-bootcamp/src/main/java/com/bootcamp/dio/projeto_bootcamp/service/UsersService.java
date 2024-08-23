package com.bootcamp.dio.projeto_bootcamp.service;

import com.bootcamp.dio.projeto_bootcamp.model.Users;
import com.bootcamp.dio.projeto_bootcamp.repository.UserRepository;
import com.bootcamp.dio.projeto_bootcamp.utils.models.PaginatedData;
import com.bootcamp.dio.projeto_bootcamp.utils.models.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
