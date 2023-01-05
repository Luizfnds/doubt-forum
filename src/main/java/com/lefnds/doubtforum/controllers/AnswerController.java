package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.request.AnswerRequestDTO;
import com.lefnds.doubtforum.dtos.response.AnswerResponseDTO;
import com.lefnds.doubtforum.model.Answer;
import com.lefnds.doubtforum.model.Doubt;
import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import com.lefnds.doubtforum.security.auth.TokenService;
import com.lefnds.doubtforum.services.AnswerService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/api/v1/answer" )
public class AnswerController {

    @Autowired
    private DoubtService doubtService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity< Page< AnswerResponseDTO > > getAllAnswers( @PageableDefault( page = 0 , size = 10 , sort = "answerDate" , direction = Sort.Direction.DESC ) Pageable pageable ) {

        List< AnswerResponseDTO > answerDtoList = answerService.getAll( pageable )
                .stream().map( AnswerResponseDTO::createAnswerResponseDTO )
                .toList();

        Page< AnswerResponseDTO > page = new PageImpl<>( answerDtoList );

        return ResponseEntity.status( HttpStatus.OK ).body( page );

    }

    @GetMapping("/{id}")
    public ResponseEntity< AnswerResponseDTO > getOneAnswer( @PathVariable UUID id ) {

        Answer answer = answerService.getOne( id )
                .orElseThrow();

        return ResponseEntity.status( HttpStatus.OK ).body( AnswerResponseDTO.createAnswerResponseDTO( answer ) );

    }

    @PostMapping("/{id}")
    public ResponseEntity< AnswerResponseDTO > createAnswer( @PathVariable UUID id ,
                                                            @RequestHeader( "Authorization" ) String token ,
                                                            @RequestBody @Valid AnswerRequestDTO answerRequestDTO ) {

        User user = userRepository.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

        Doubt doubt = doubtService.getOne( id )
                .orElseThrow();

        Answer answer = answerService.save( Answer.builder()
                        .answerDate( LocalDateTime.now( ZoneId.of("UTC") ) )
                        .user( user )
                        .doubt( doubt)
                        .content( answerRequestDTO.getContent() )
                        .build());

        return ResponseEntity.status( HttpStatus.CREATED ).body( AnswerResponseDTO.createAnswerResponseDTO( answer ) );

    }

    @PutMapping("/{id}")
    public ResponseEntity< Object > alterAnswer( @RequestHeader( "Authorization" ) String token ,
                                                @PathVariable UUID id ,
                                                @RequestBody @Valid AnswerRequestDTO answerRequestDTO ) {

        Answer answer = answerService.getOne( id )
                .orElseThrow();

        User user = userRepository.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

        boolean containsAnswerId = user.getAnswers().stream()
                .map( Answer::getAnswerId )
                .toList()
                .contains(id);

        if( !containsAnswerId ) {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "This answer does not belong to the logged in user." );
        }

        answer.setContent( answerRequestDTO.getContent() );

        answerService.save( answer );

        return ResponseEntity.status( HttpStatus.OK ).body( AnswerResponseDTO.createAnswerResponseDTO( answer ) );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity< String > deleteAnswer( @RequestHeader( "Authorization" ) String token ,
                                                  @PathVariable UUID id ) {

        Answer answer = answerService.getOne( id )
                .orElseThrow();

        User user = userRepository.findByEmail( tokenService.getSubject( token ) )
                .orElseThrow();

       boolean containsAnswerId = user.getAnswers().stream()
                .map( Answer::getAnswerId )
                .toList()
                .contains(id);

        if( !containsAnswerId ) {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "This answer does not belong to the logged in user." );
        }

        answerService.delete( answer );

        return ResponseEntity.status( HttpStatus.OK ).body( "Answer deleted successfully" );

    }

}
