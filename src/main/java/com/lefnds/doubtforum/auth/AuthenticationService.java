package com.lefnds.doubtforum.auth;

import com.lefnds.doubtforum.auth.exceptions.EmailAlreadyInUseException;
import com.lefnds.doubtforum.config.TokenService;
import com.lefnds.doubtforum.dtos.LoginDataDto;
import com.lefnds.doubtforum.dtos.UserDto;
import com.lefnds.doubtforum.enums.Role;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse registry( UserDto userDto ) {

        if( userRepository.findByEmail( userDto.getEmail() ).isPresent() ) {
            throw new EmailAlreadyInUseException();
        }

        User user = User.builder()
                .name( userDto.getName() )
                .email( userDto.getEmail() )
                .birth( userDto.getBirth() )
                .password( passwordEncoder.encode( userDto.getPassword() ) )
                .creationDate( LocalDateTime.now( ZoneId.of( "UTC" ) ))
                .role( Role.USER )
                .build();

        userRepository.save( user );

        String token = tokenService.generateToken( user );

        return AuthenticationResponse.builder()
                .token( token )
                .build();

    }

    public AuthenticationResponse authenticate( LoginDataDto loginDataDto ) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDataDto.getEmail() , loginDataDto.getPassword()
        );

        authenticationManager.authenticate( usernamePasswordAuthenticationToken );

        User user = userRepository.findByEmail( loginDataDto.getEmail() )
                .orElseThrow(  );

        String token = tokenService.generateToken( user );

        return AuthenticationResponse.builder()
                .token( token )
                .build();

    }
}
