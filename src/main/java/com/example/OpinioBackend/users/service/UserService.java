package com.example.OpinioBackend.users.service;

import com.example.OpinioBackend.users.model.UserModel;
import com.example.OpinioBackend.users.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    public UserModel findByUsername(String username){
        UserModel userModel = usersRepository.findByUsername(username).orElseThrow();
        return  userModel;
    }


}
