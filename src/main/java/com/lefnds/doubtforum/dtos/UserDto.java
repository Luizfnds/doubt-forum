package com.lefnds.doubtforum.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class UserDto {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private LocalDateTime birth;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 4)
    private String password;

}
