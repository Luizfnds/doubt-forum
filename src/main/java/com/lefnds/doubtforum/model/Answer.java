package com.lefnds.doubtforum.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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
    private LocalDateTime answerDate;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doubt_id")
    private Doubt doubt;
}
