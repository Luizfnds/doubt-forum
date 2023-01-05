package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.response.DoubtResponseDTO;
import com.lefnds.doubtforum.dtos.request.UserRequestDTO;
import com.lefnds.doubtforum.dtos.response.UserResponseDTO;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import com.lefnds.doubtforum.security.auth.TokenService;
import com.lefnds.doubtforum.services.UserService;
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

    @GetMapping
    public ResponseEntity< UserResponseDTO > getUser( @RequestHeader( "Authorization" ) String token ) {

        User user = userRepository.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

        return ResponseEntity.status( HttpStatus.OK ).body( UserResponseDTO.createUserResponseDTO( user ) );

    }

    @PutMapping
    public ResponseEntity< UserResponseDTO > alterUserData( @RequestHeader( "Authorization" ) String token ,
                                                            @RequestBody @Valid UserRequestDTO userRequestDTO ) {

        User user = userRepository.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

        user.setName( userRequestDTO.getName() );
        user.setBirth( userRequestDTO.getBirth() );
        user.setPassword( passwordEncoder.encode( userRequestDTO.getPassword() ) );

        return ResponseEntity.status( HttpStatus.OK ).body( UserResponseDTO.createUserResponseDTO( userService.save( user ) ) );

    }

    @DeleteMapping
    public ResponseEntity< String > deleteUser( @RequestHeader( "Authorization" ) String token ) {

        User user = userRepository.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

        userService.delete( user );

        return ResponseEntity.status( HttpStatus.OK ).body( "Deleted with success" );

    }

}
