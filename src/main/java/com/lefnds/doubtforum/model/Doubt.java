package com.lefnds.doubtforum.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Doubt {

    private UUID doubtId;
    private Date doubtDate;
    private String title;
    private String content;
    private List<Answer> answers;
}
