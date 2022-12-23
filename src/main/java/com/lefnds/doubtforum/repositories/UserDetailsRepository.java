package com.lefnds.doubtforum.repositories;

import com.lefnds.doubtforum.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID> {

    public Optional<UserDetails> findByUsername(String username);
}
