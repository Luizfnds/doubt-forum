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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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

    @GetMapping
    public ResponseEntity< Page<DoubtResponseDTO> > getAllDoubts( @PageableDefault( page = 0 , size = 5 , sort = "doubtDate" , direction = Sort.Direction.DESC ) Pageable pageable) {

        List< DoubtResponseDTO > doubtDtoList = doubtService.getAll( pageable )
                .stream().map( ( doubt ) -> { return DoubtResponseDTO.builder()
                                .doubtDate( doubt.getDoubtDate() )
                                .nameOfUser( doubt.getUser().getName() )
                                .title( doubt.getTitle() )
                                .content( doubt.getContent() )
                                .answers( doubt.getAnswers() )
                                .build(); })
                .toList();

        Page< DoubtResponseDTO > page = new PageImpl<>( doubtDtoList );

        return ResponseEntity.status( HttpStatus.OK ).body( page );

    }

    @PostMapping
    public ResponseEntity< DoubtResponseDTO > createDoubt( @RequestHeader( "Authorization" ) String token ,
                                                           @RequestBody @Valid DoubtRequestDTO doubtRequestDTO ) {

        String username = tokenService.decodeToken( token ).getSubject();
        User user = userRepository.findByEmail( username )
                .orElseThrow();

        Doubt doubt = Doubt.builder()
                .doubtDate( LocalDateTime.now( ZoneId.of("UTC") ) )
                .user( user )
                .title( doubtRequestDTO.getTitle() )
                .content( doubtRequestDTO.getContent() )
                .answers( new ArrayList<>() )
                .build();

        doubtService.save( doubt );

        DoubtResponseDTO doubtResponseDTO = DoubtResponseDTO.builder()
                .doubtDate( doubt.getDoubtDate() )
                .nameOfUser( doubt.getUser().getName() )
                .title( doubt.getTitle() )
                .content( doubt.getContent() )
                .answers( doubt.getAnswers() )
                .build();

        return ResponseEntity.status( HttpStatus.OK ).body( doubtResponseDTO );

    }

}
