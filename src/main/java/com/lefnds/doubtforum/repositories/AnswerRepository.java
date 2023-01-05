package com.lefnds.doubtforum.repositories;

import com.lefnds.doubtforum.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository< Answer , UUID > {
}
