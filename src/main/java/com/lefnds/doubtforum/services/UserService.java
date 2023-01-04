package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.security.auth.TokenService;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public Optional<User> findById( UUID id ) {

        return userRepository.findById( id );

    }

    @Transactional
    public User save( User user ) {

        return userRepository.save( user );

    }

    @Transactional
    public void delete(User user ) {

        userRepository.delete( user );

    }



}
