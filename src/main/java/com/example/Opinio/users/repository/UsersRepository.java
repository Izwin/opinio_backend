package com.example.Opinio.users.repository;

import com.example.Opinio.users.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserModel,Long> {

    Optional<UserModel> findByUsername(String username);
}
