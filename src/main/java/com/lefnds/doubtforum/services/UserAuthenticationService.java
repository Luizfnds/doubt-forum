package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.exceptions.ExistingEmailException;
import com.lefnds.doubtforum.exceptions.InvalidLoginException;
import com.lefnds.doubtforum.model.DadosLogin;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    private final UserRepository repository;

    public UserAuthenticationService(UserRepository repository) {
        this.repository = repository;
    }

    public User auth(DadosLogin dados) {
        User user = repository.findByEmail(dados.getEmail()).orElseThrow(ExistingEmailException::new);
        if(user.getPassword().equals(dados.getPassword())) {
            return user;
        } else {
            throw new InvalidLoginException();
        }
    }
}
