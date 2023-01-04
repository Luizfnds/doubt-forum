package com.lefnds.doubtforum.config;

import com.lefnds.doubtforum.exceptions.ExpiredTokenException;
import com.lefnds.doubtforum.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final long EXPIRATION_TIME = 30 * 60000;
    private static final String SECRET_KEY = "6E3272357538782F413F4428472B4B6150645367566B59703373367639792442";

    public String generateToken( User user ) {

        return Jwts
                .builder()
                .setSubject( user.getUsername() )
                .setIssuedAt( new Date( System.currentTimeMillis() ) )
                .setExpiration( new Date( System.currentTimeMillis() + EXPIRATION_TIME ) )
                .signWith( SignatureAlgorithm.HS256 , SECRET_KEY )
                .compact();

    }

    public Claims decodeToken( String token ) {

        return Jwts
                .parser()
                .setSigningKey( SECRET_KEY )
                .parseClaimsJws( token )
                .getBody();

    }

    public Boolean isTokenExpired( String token ) {

        String treatedToken = token.replace( "Bearer " , "" );

        Claims claims = decodeToken( treatedToken );

        if( claims.getExpiration().before( new Date( System.currentTimeMillis() ) ) ) {
            throw new ExpiredTokenException();
        }

        return false;

    }

    public Boolean isTokenValid( String token , UserDetails userDetails ) {

        String username = decodeToken( token ).getSubject();

        return ( username.equals( userDetails.getUsername() ) ) && !isTokenExpired( token );

    }

}
