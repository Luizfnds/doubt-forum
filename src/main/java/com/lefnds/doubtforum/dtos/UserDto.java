package com.lefnds.doubtforum.dtos;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
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
