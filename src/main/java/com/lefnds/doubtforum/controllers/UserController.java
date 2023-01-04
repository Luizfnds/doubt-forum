package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.UserRequestDTO;
import com.lefnds.doubtforum.dtos.UserResponseDTO;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import com.lefnds.doubtforum.security.auth.TokenService;
import com.lefnds.doubtforum.security.auth.AuthenticationService;
import com.lefnds.doubtforum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/v1/demo" )
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService userAuthenticationService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<UserResponseDTO> getUser( @RequestHeader( "Authorization" ) String token ) {

        String username = tokenService.decodeToken( token ).getSubject();
        User user = userRepository.findByEmail( username )
                .orElseThrow();

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .name( user.getName() )
                .email( user.getEmail() )
                .birth( user.getBirth() )
                .doubts( user.getDoubts() )
                .build();

        return ResponseEntity.status( HttpStatus.OK ).body( userResponseDTO );

    }

    @PutMapping
    public ResponseEntity< Void > alterUserData( @RequestHeader( "Authorization" ) String token ,
                                                 @RequestBody UserRequestDTO userRequestDTO ) {

        String username = tokenService.decodeToken( token ).getSubject();
        User user = userRepository.findByEmail( username )
                .orElseThrow();

        user.setName( userRequestDTO.getName() );
        user.setBirth( userRequestDTO.getBirth() );
        user.setPassword( passwordEncoder.encode( userRequestDTO.getPassword() ) );

        userService.save( user );

        return ResponseEntity.status( HttpStatus.OK ).build();

    }

//    @GetMapping( "/all" )
//    public ResponseEntity< Page<User> > findAllUsers( @PageableDefault( page = 0 , size = 10 , sort = "userId" , direction = Sort.Direction.ASC ) Pageable pageable ) {
//
//        return ResponseEntity.status( HttpStatus.OK ).body( userService.findAll( pageable ) );
//
//    }


}
