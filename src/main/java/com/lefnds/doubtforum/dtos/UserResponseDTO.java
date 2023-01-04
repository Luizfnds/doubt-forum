package com.lefnds.doubtforum.dtos;

import com.lefnds.doubtforum.model.Doubt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDTO {

    private String name;
    private String email;
    private LocalDateTime birth;
    private List< Doubt > doubts;

}
