package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.DoubtRequestDTO;
import com.lefnds.doubtforum.dtos.DoubtResponseDTO;
import com.lefnds.doubtforum.dtos.UserRequestDTO;
import com.lefnds.doubtforum.model.Doubt;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import com.lefnds.doubtforum.security.auth.TokenService;
import com.lefnds.doubtforum.services.DoubtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/v1/doubt" )
public class DoubtController {

    @Autowired
    public DoubtService doubtService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoubtResponseDTO doubtResponseDTO;

    @GetMapping
    public ResponseEntity< Page<DoubtResponseDTO> > getAllDoubts( @PageableDefault( page = 0 , size = 5 , sort = "doubtDate" , direction = Sort.Direction.DESC ) Pageable pageable) {

        List< DoubtResponseDTO > doubtDtoList = doubtService.getAll( pageable )
                .stream().map( ( doubt ) -> { return doubtResponseDTO.createDoubtResponseDTO( doubt ); })
                .toList();

        Page< DoubtResponseDTO > page = new PageImpl<>( doubtDtoList );

        return ResponseEntity.status( HttpStatus.OK ).body( page );

    }

    @GetMapping("/{id}")
    public ResponseEntity< DoubtResponseDTO > getOneDoubt( @PathVariable UUID id ) {

        Doubt doubt = doubtService.getOne( id )
                .orElseThrow();

        return ResponseEntity.status( HttpStatus.OK ).body( doubtResponseDTO.createDoubtResponseDTO( doubt ) );

    }

    @PostMapping
    public ResponseEntity< DoubtResponseDTO > createDoubt( @RequestHeader( "Authorization" ) String token ,
                                                           @RequestBody @Valid DoubtRequestDTO doubtRequestDTO ) {

        String username = tokenService.decodeToken( token ).getSubject();
        User user = userRepository.findByEmail( username )
                .orElseThrow();

        Doubt doubt = doubtService.save( Doubt.builder()
                .doubtDate( LocalDateTime.now( ZoneId.of("UTC") ) )
                .user( user )
                .title( doubtRequestDTO.getTitle() )
                .content( doubtRequestDTO.getContent() )
                .answers( new ArrayList<>() )
                .build() );

        return ResponseEntity.status( HttpStatus.OK ).body( doubtResponseDTO.createDoubtResponseDTO( doubt ) );

    }

    @PutMapping("/{id}")
    public ResponseEntity< Object > alterDoubt( @RequestHeader( "Authorization" ) String token ,
                                                 @PathVariable UUID id ,
                                                 @RequestBody @Valid DoubtRequestDTO doubtRequestDTO ) {

        Doubt doubt = doubtService.getOne( id )
                .orElseThrow();

        User user = userRepository.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

        List< UUID > doubtList = user.getDoubts().stream()
                .map( Doubt::getDoubtId )
                .filter( (u) -> u.equals( doubt.getDoubtId() ) )
                .toList();

        if( doubtList.isEmpty() ) {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "This doubt does not belong to the logged in user." );
        }

        doubt.setTitle( doubtRequestDTO.getTitle() );
        doubt.setContent( doubtRequestDTO.getContent() );

        doubtService.save( doubt );

        return ResponseEntity.status( HttpStatus.OK ).body( doubtResponseDTO.createDoubtResponseDTO( doubt ) );

    }

//    @DeleteMapping
//    public ResponseEntity< String > getOneDoubt(  ) {
//
//
//    }

}
