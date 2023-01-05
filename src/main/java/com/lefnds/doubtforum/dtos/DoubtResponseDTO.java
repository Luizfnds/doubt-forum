package com.lefnds.doubtforum.dtos;

import com.lefnds.doubtforum.model.Answer;
import com.lefnds.doubtforum.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoubtResponseDTO {

    private LocalDateTime doubtDate;
    private String nameOfUser;
    private String title;
    private String content;
    private List<Answer> answers;

}
