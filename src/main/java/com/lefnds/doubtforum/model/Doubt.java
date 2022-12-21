package com.lefnds.doubtforum.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_DOUBT")
public class Doubt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID doubtId;
    @Column(nullable = false)
    private Date doubtDate;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
//    private User author;
//    private List<Answer> answers;
}
