package com.lefnds.doubtforum.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Answer {

    private UUID answerId;
    private Date answerDate;
    private String content;
}
