package com.lefnds.doubtforum.repositories;

import com.lefnds.doubtforum.model.Doubt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoubtRepository extends JpaRepository< Doubt, UUID > {
}
