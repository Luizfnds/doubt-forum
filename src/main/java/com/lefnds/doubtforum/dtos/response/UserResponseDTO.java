package com.lefnds.doubtforum.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDTO {

    private String name;
    private String email;
    private LocalDateTime birth;
    private List< DoubtResponseDTO > doubts;

}
