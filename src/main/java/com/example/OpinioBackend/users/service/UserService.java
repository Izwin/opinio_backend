package com.example.OpinioBackend.users.service;

import com.example.OpinioBackend.users.model.UserModel;
import com.example.OpinioBackend.users.repository.UsersRepository;
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
