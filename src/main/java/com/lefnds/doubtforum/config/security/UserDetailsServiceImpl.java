package com.lefnds.doubtforum.config.security;

import com.lefnds.doubtforum.model.User;
import com.lefnds.doubtforum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Optional< User > user = userRepository.findByEmail( username );
        if( user.isEmpty() ) {
            throw new UsernameNotFoundException("Email " + username + " not registered");
        }
        return new com.lefnds.doubtforum.config.security.UserDetails( user.get() );
    }
    
}
