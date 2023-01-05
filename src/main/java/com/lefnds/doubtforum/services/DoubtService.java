package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.model.Doubt;
import com.lefnds.doubtforum.repositories.DoubtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoubtService {

    @Autowired
    private DoubtRepository doubtRepository;

    public Page< Doubt > getAll( Pageable pageable ) {

        return doubtRepository.findAll( pageable );

    }

}
