package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.repositories.UserRepository;
import com.lefnds.doubtforum.security.auth.TokenService;
import com.lefnds.doubtforum.security.auth.AuthenticationService;
import com.lefnds.doubtforum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity< String > sayHello() {
        return ResponseEntity.ok( "hello" );
    }

//    @GetMapping()
//    public Object getUser( @RequestHeader( name = "Authorization" ) String token ) {
//
//        if( !userAuthenticationService.verifyToken( token ) ) {
//            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "Error: Invalid token" );
//        }
//
//        String treatedToken = token.replace("Bearer " , "" );
//
//        Optional<User> user = userRepository.findById( UUID.fromString( tokenService.decodeToken( treatedToken ).getSubject() ) );
//
//        if ( user.isEmpty() ) {
//            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "User not found ");
//        }
//
//        return ResponseEntity.status( HttpStatus.OK ).body( user.get() );
//
//    }

//    @PostMapping
//    public Object createUser( @RequestBody @Valid UserDto userDto ) {
//
//        User user = new User();
//        user.fromDto( userDto );
//        user.setCreationDate( LocalDateTime.now( ZoneId.of( "UTC" ) ) );
//
//        return ResponseEntity.status( HttpStatus.CREATED ).body( userService.save(user) );
//
//    }

//    @GetMapping( "/all" )
//    public ResponseEntity< Page<User> > findAllUsers( @PageableDefault( page = 0 , size = 10 , sort = "userId" , direction = Sort.Direction.ASC ) Pageable pageable ) {
//
//        return ResponseEntity.status( HttpStatus.OK ).body( userService.findAll( pageable ) );
//
//    }


}
