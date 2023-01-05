package com.lefnds.doubtforum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
