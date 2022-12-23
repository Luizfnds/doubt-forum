package com.lefnds.doubtforum.services;

import com.lefnds.doubtforum.repositories.UserDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+username+" not fount."));
        return new User(userDetails.getUsername(), userDetails.getPassword(), true, true, true, true, userDetails.getAuthorities());
    }
}
