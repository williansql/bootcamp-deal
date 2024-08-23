package com.bootcamp.dio.projeto_bootcamp.repository;

import com.bootcamp.dio.projeto_bootcamp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {


}
