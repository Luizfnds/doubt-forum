package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping( "/{id}" )
    public Object findUserById(@PathVariable UUID id) {

        Optional< User > user = userService.findById(id);
        if ( ( user.isEmpty() ) ) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "User not found ");
        }
        return ResponseEntity.status( HttpStatus.OK ).body( user.get() );

    }

    @GetMapping
    public ResponseEntity< Page< User >> findAllUsers( @PageableDefault( page = 0 , size = 10 , sort = "userId" , direction = Sort.Direction.ASC ) Pageable pageable) {

        return ResponseEntity.status( HttpStatus.OK ).body( userService.findAll(pageable) );

    }

}
