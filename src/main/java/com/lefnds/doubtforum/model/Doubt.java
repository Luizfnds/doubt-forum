package com.lefnds.doubtforum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_DOUBT")
public class Doubt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID doubtId;
    @Column(nullable = false)
    private LocalDateTime doubtDate;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "doubt", fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();

}
