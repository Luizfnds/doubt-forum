package com.lefnds.doubtforum.dtos;

import com.lefnds.doubtforum.model.Answer;
import com.lefnds.doubtforum.model.Doubt;
import com.lefnds.doubtforum.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DoubtResponseDTO {

    private UUID doubtId;
    private LocalDateTime doubtDate;
    private String nameOfUser;
    private String title;
    private String content;
    private List<Answer> answers;

    public DoubtResponseDTO createDoubtResponseDTO( Doubt doubt ) {

        return DoubtResponseDTO.builder()
                .doubtId( doubt.getDoubtId() )
                .doubtDate( doubt.getDoubtDate() )
                .nameOfUser( doubt.getUser().getName() )
                .title( doubt.getTitle() )
                .content( doubt.getContent() )
                .answers( doubt.getAnswers() )
                .build();

    }

}
