package com.lefnds.doubtforum.dtos;

import com.lefnds.doubtforum.model.User;
import lombok.Data;

@Data
public class UserAuthenticateDto {

    private String name;
    private String email;
    private String token;
    private String type;

    public UserAuthenticateDto() { }

    public UserAuthenticateDto( String name , String email , String token , String type ) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.type = type;
    }

    public static UserAuthenticateDto toDto( User user , String type) {
        return new UserAuthenticateDto( user.getName() , user.getEmail() , user.getToken() , type );
    }
}
