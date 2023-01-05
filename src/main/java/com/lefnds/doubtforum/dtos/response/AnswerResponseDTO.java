package com.lefnds.doubtforum.dtos.response;

import com.lefnds.doubtforum.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AnswerResponseDTO {

    private UUID answerId;
    private LocalDateTime answerDate;
    private String nameOfUser;
    private String content;

    public AnswerResponseDTO createAnswerResponseDTO( Answer answer ) {

        return AnswerResponseDTO.builder()
                .answerId( answer.getAnswerId() )
                .answerDate( answer.getAnswerDate() )
                .nameOfUser( answer.getUser().getName() )
                .content( answer.getContent() )
                .build();

    }

}
