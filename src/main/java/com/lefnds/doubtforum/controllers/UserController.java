package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.UserDto;
import com.lefnds.doubtforum.enums.RoleName;
import com.lefnds.doubtforum.model.Role;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.model.UserDetails;
import com.lefnds.doubtforum.repositories.UserDetailsRepository;
import com.lefnds.doubtforum.services.UserDetailsServiceImpl;
import com.lefnds.doubtforum.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping( "/{id}" )
    public Object findUserById( @PathVariable UUID id ) {

        Optional< User > user = userService.findById( id );
        if ( user.isEmpty() ) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "User not found ");
        }
        return ResponseEntity.status( HttpStatus.OK ).body( user.get() );

    }

    @GetMapping
    public ResponseEntity< Page<User> > findAllUsers( @PageableDefault( page = 0 , size = 10 , sort = "userId" , direction = Sort.Direction.ASC ) Pageable pageable ) {

        return ResponseEntity.status( HttpStatus.OK ).body( userService.findAll( pageable ) );

    }

    @PostMapping
    public Object createUser( @RequestBody @Valid UserDto userDto ) {

        User user = new User();
        UserDetails userDetails = new UserDetails();
//        Role role = new Role();

        user.fromDto( userDto );
        userDetailsServiceImpl.fromDto( userDto , userDetails );
//        role.setRoleName( RoleName.ROLE_USER );
        user.setCreationDate( LocalDateTime.now( ZoneId.of( "UTC" ) ) );
//        userDetails.getRoles().add( role );

        userDetailsRepository.save( userDetails );
        return ResponseEntity.status( HttpStatus.CREATED ).body( userService.save(user) );

    }



}
