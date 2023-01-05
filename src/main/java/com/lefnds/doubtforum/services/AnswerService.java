package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.model.Answer;
import com.lefnds.doubtforum.model.Doubt;
import com.lefnds.doubtforum.repositories.AnswerRepository;
import com.lefnds.doubtforum.repositories.DoubtRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

//    public Page<Answer> getAll(Pageable pageable ) {
//
//        return answerRepository.findAll( pageable );
//
//    }

    public Optional< Answer > getOne( UUID id ) {

        return answerRepository.findById( id );

    }

    @Transactional
    public Answer save( Answer answer ) {

        return answerRepository.save( answer );

    }

    @Transactional
    public void delete( Answer answer ) {

        answerRepository.delete( answer );

    }

}
