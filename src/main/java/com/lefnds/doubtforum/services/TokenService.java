package com.lefnds.doubtforum.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.lefnds.doubtforum.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final long expirationTime = 1800000;
    private String key = "key for creation of token";

    public String generateToken() {

        return Jwts.builder()
                .setIssuedAt( new Date( System.currentTimeMillis() ) )
                .setSubject( "JWT-test" )
                .setExpiration( new Date( System.currentTimeMillis() + expirationTime ) )
                .signWith( SignatureAlgorithm.HS256 , key )
                .compact();

    }

    public Claims decodeToken( String token ) {

        return Jwts.parser()
                .setSigningKey( key )
                .parseClaimsJws( token )
                .getBody();

    }

}
