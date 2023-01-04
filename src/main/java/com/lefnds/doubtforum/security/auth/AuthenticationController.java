package com.lefnds.doubtforum.security.auth;

import com.lefnds.doubtforum.security.auth.dtos.AuthenticationResponseDTO;
import com.lefnds.doubtforum.security.auth.dtos.LoginRequestDTO;
import com.lefnds.doubtforum.security.auth.dtos.RegisterRequestDTO;
import com.lefnds.doubtforum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping( "/register" )
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO userDto ) {

        return ResponseEntity.status( HttpStatus.OK ).body( authenticationService.registry( userDto ) );

    }

    @PostMapping( "/authenticate" )
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody LoginRequestDTO loginData ){

        return ResponseEntity.status( HttpStatus.OK ).body( authenticationService.authenticate( loginData ) );

    }

}
