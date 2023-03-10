package com.lefnds.doubtforum.dtos.response;

import com.lefnds.doubtforum.model.Doubt;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
    private List<AnswerResponseDTO> answers;

    public static DoubtResponseDTO createDoubtResponseDTO( Doubt doubt ) {

        return DoubtResponseDTO.builder()
                .doubtId( doubt.getDoubtId() )
                .doubtDate( doubt.getDoubtDate() )
                .nameOfUser( doubt.getUser().getName() )
                .title( doubt.getTitle() )
                .content( doubt.getContent() )
                .answers( doubt.getAnswers().stream()
                        .map( AnswerResponseDTO::createAnswerResponseDTO )
                        .toList() )
                .build();

    }

}
