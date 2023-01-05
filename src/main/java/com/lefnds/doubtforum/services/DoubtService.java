package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.model.Doubt;
import com.lefnds.doubtforum.repositories.DoubtRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoubtService {

    @Autowired
    private DoubtRepository doubtRepository;

    public Page< Doubt > getAll( Pageable pageable ) {

        return doubtRepository.findAll( pageable );

    }

    public Optional< Doubt > getOne( UUID id ) {

        return doubtRepository.findById( id );

    }

    @Transactional
    public Doubt save( Doubt doubt ) {

        return doubtRepository.save( doubt );

    }

    @Transactional
    public void delete( Doubt doubt ) {

        doubtRepository.delete( doubt );

    }

}
