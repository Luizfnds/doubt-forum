package com.lefnds.doubtforum.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ANSWER")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID answerId;
    @Column(nullable = false)
    private Date answerDate;
    @Column(nullable = false)
    private String content;
//    private Doubt doubt;
}
