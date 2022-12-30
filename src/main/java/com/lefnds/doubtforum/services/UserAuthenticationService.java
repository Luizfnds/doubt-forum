package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.exceptions.ExistingEmailException;
import com.lefnds.doubtforum.exceptions.ExpiredTokenException;
import com.lefnds.doubtforum.exceptions.InvalidLoginException;
import com.lefnds.doubtforum.dtos.LoginDataDto;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import io.jsonwebtoken.Claims;
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

    public User authorizes(LoginDataDto loginData , String token ) {

        User user = userRepository.findByEmail( loginData.getEmail() ).orElseThrow( ExistingEmailException::new );

        if( loginData.getPassword().equals( user.getPassword() ) && !token.isEmpty() && validate(token) ) {
            return user;
        } else {
            throw new InvalidLoginException();
        }

    }

    public Boolean validate( String token ) {

        String treatedToken = token.replace( "Bearer " , "" );

        Claims claims = tokenService.decodeToken( treatedToken );

        if( claims.getExpiration().before( new Date( System.currentTimeMillis() ) ) ) {
            throw new ExpiredTokenException();
        }

        return true;

    }

}
