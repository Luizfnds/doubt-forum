package com.lefnds.doubtforum.auth;

import com.lefnds.doubtforum.config.TokenService;
import com.lefnds.doubtforum.dtos.LoginDataDto;
import com.lefnds.doubtforum.dtos.UserDto;
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
    public ResponseEntity< AuthenticationResponse > register( @RequestBody UserDto userDto ) {

        return ResponseEntity.status( HttpStatus.OK ).body( authenticationService.registry( userDto ) );

    }

    @PostMapping( "/authenticate" )
    public ResponseEntity< AuthenticationResponse > authenticate( @RequestBody LoginDataDto loginData ){

        return ResponseEntity.status( HttpStatus.OK ).body( authenticationService.authenticate( loginData ) );

    }

}
