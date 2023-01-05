package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.DoubtResponseDTO;
import com.lefnds.doubtforum.dtos.UserRequestDTO;
import com.lefnds.doubtforum.dtos.UserResponseDTO;
import com.lefnds.doubtforum.model.Doubt;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import com.lefnds.doubtforum.security.auth.TokenService;
import com.lefnds.doubtforum.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/user" )
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DoubtResponseDTO doubtResponseDTO;

    @GetMapping
    public ResponseEntity< UserResponseDTO > getUser( @RequestHeader( "Authorization" ) String token ) {

        String username = tokenService.decodeToken( token ).getSubject();
        User user = userRepository.findByEmail( username )
                .orElseThrow();

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .name( user.getName() )
                .email( user.getEmail() )
                .birth( user.getBirth() )
                .doubts( user.getDoubts().stream()
                        .map( (d) -> { return doubtResponseDTO.createDoubtResponseDTO( d ); } )
                        .toList())
                .build();

        return ResponseEntity.status( HttpStatus.OK ).body( userResponseDTO );

    }

    @PutMapping
    @Transactional
    public ResponseEntity< Void > alterUserData( @RequestHeader( "Authorization" ) String token ,
                                                 @RequestBody @Valid UserRequestDTO userRequestDTO ) {

        String username = tokenService.decodeToken( token ).getSubject();
        User user = userRepository.findByEmail( username )
                .orElseThrow();

        user.setName( userRequestDTO.getName() );
        user.setBirth( userRequestDTO.getBirth() );
        user.setPassword( passwordEncoder.encode( userRequestDTO.getPassword() ) );

        userService.save( user );

        return ResponseEntity.status( HttpStatus.OK ).build();

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity< Void > deleteUser( @RequestHeader( "Authorization" ) String token ) {

        String username = tokenService.decodeToken( token ).getSubject();
        User user = userRepository.findByEmail( username )
                .orElseThrow();

        userService.delete( user );

        return ResponseEntity.status( HttpStatus.OK ).build();

    }

}
