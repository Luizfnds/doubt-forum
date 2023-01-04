package com.lefnds.doubtforum.security.auth.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRequestDTO {

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private LocalDateTime birth;
    @Size(min = 4)
    @NotBlank
    private String password;

}
