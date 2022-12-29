package com.lefnds.doubtforum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lefnds.doubtforum.dtos.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


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
    private String password;
    @Column(nullable = false)
    private LocalDateTime birth;
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Doubt> doubts;

//    @OneToOne(mappedBy = "user")
//    private UserDetails userDetails;

    public void fromDto( UserDto userDto ) {

        this.setName( userDto.getName() );
        this.setEmail( userDto.getEmail() );
        this.setBirth( userDto.getBirth() );
        this.setPassword( userDto.getPassword() );

    }

}
