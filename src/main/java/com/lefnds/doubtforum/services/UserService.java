package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import com.lefnds.doubtforum.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
