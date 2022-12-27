package com.lefnds.doubtforum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lefnds.doubtforum.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ROLE")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private List<UserDetails> users;

    @Override
    public String getAuthority() {
        return roleName.toString();
    }

}
