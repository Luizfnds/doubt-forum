package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.LoginDataDto;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.services.TokenService;
import com.lefnds.doubtforum.services.UserAuthenticationService;
import com.lefnds.doubtforum.services.UserService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    // Autentica o usuario
    @GetMapping
    public void authenticateUser(@RequestBody LoginDataDto loginData ,
                                                  HttpServletResponse response) {

        User user = userAuthenticationService.authenticateUser( loginData );
        String token = tokenService.generateToken( user ) ;

        Cookie cookie = new Cookie( "token" , token );
        cookie.setPath( "/" );
        cookie.setHttpOnly( true );
        cookie.setMaxAge( 60 * 30 ); // 30 min

        response.addCookie( cookie );

    }

    @GetMapping("/sla")
    public void authorize(@CookieValue String token ,
                          ServletRequest request ) {

        if( userAuthenticationService.verifyToken( token ) ) {
            userService.findById( UUID.fromString( tokenService.decodeToken( token ).getSubject() ) );
        }

    }



}
