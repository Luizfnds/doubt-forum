package com.lefnds.doubtforum.controllers;

import com.lefnds.doubtforum.dtos.DoubtResponseDTO;
import com.lefnds.doubtforum.services.DoubtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/v1/doubt" )
public class DoubtController {

    @Autowired
    public DoubtService doubtService;

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

}
