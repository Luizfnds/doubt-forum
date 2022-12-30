package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.exceptions.ExistingEmailException;
import com.lefnds.doubtforum.exceptions.ExpiredTokenException;
import com.lefnds.doubtforum.exceptions.InvalidLoginException;
import com.lefnds.doubtforum.model.DadosLogin;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserAuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserAuthenticationService( UserRepository userRepository , TokenService tokenService ) {

        this.userRepository = userRepository;
        this.tokenService = tokenService;

    }

    public User authorizes( DadosLogin dados , String token ) {

        User user = userRepository.findByEmail( dados.getEmail() ).orElseThrow( ExistingEmailException::new );

        if( dados.getPassword().equals( user.getPassword() ) && !token.isEmpty() && validate(token) ) {
            return user;
        } else {
            throw new InvalidLoginException();
        }

    }

    public Boolean validate( String token ) {

        String tratedToken = token.replace( "Bearer " , "" );

        Claims claims = tokenService.decodeToken( tratedToken );

        if( claims.getExpiration().before( new Date( System.currentTimeMillis() ) ) ) {
            throw new ExpiredTokenException();
        }

        return true;

    }

}
