package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.exceptions.ExistingEmailException;
import com.lefnds.doubtforum.exceptions.InvalidLoginException;
import com.lefnds.doubtforum.model.DadosLogin;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserAuthenticationService( UserRepository userRepository , TokenService tokenService ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User auth(DadosLogin dados) {

        User user = userRepository.findByEmail( dados.getEmail() ).orElseThrow( ExistingEmailException::new );
        if( dados.getPassword().equals( user.getPassword() ) ) {
            String token = tokenService.generateToken( user );
            System.out.println( token );
            return user;
        } else {
            throw new InvalidLoginException();
        }

    }
}
