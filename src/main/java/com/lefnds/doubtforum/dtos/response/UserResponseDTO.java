package com.lefnds.doubtforum.dtos.response;

import com.lefnds.doubtforum.model.Answer;
import com.lefnds.doubtforum.model.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private LocalDateTime creationDate;
    private String name;
    private String email;
    private LocalDateTime birth;
    private List< DoubtResponseDTO > doubts;
    private List< AnswerResponseDTO > answers;

    public static UserResponseDTO createUserResponseDTO ( User user ) {

        return UserResponseDTO.builder()
                .creationDate( user.getCreationDate() )
                .name( user.getName() )
                .email( user.getEmail() )
                .birth( user.getBirth() )
                .doubts( user.getDoubts().stream()
                        .map( DoubtResponseDTO::createDoubtResponseDTO )
                        .toList() )
                .answers( user.getAnswers().stream()
                        .map( AnswerResponseDTO::createAnswerResponseDTO )
                        .toList() )
                .build();

    }

}
