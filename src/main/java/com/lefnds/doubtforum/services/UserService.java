package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.dtos.UserDto;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Page<User> findAll( Pageable pageable ) {

        return userRepository.findAll( pageable );

    }

    @Transactional
    public User save( User user ) {

        return userRepository.save( user );

    }

    public User registerUser( User user ) {

        tokenService.generateToken( user );
        return userRepository.save( user );

    }

}
