package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.LoginDataDto;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.services.TokenService;
import com.lefnds.doubtforum.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity< String > authorize( @RequestBody LoginDataDto loginData ,
                                            @RequestHeader String authorization) {

        User user = userAuthenticationService.authorizes( loginData , authorization );
        System.out.println( tokenService.generateToken( user ) );

        return ResponseEntity.status( HttpStatus.ACCEPTED ).body( "Bearer " + tokenService.generateToken( user ) ) ;

    }



}
