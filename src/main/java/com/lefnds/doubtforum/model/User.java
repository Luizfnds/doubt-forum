package com.lefnds.doubtforum.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDateTime birth;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Doubt> doubts;
}
