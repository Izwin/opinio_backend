package com.example.Opinio.users.service;

import com.example.Opinio.users.model.UserModel;
import com.example.Opinio.users.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    public UserModel findByUsername(String username){
        return usersRepository.findByUsername(username).orElseThrow();
    }
}
