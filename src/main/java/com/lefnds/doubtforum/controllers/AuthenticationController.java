package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.UserAuthenticateDto;
import com.lefnds.doubtforum.model.DadosLogin;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.services.TokenService;
import com.lefnds.doubtforum.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<UserAuthenticateDto> AuthTest( @RequestBody DadosLogin dados ,
                                                         @RequestHeader String authorization) {

        User user = userAuthenticationService.authorizes( dados , authorization );

        return ResponseEntity.status( HttpStatus.ACCEPTED ).body( UserAuthenticateDto.toDto( user , "Bearer " ) );

    }

}
