package com.lefnds.doubtforum.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class User {

    private UUID userId;
    private Date creationDate;
    private String name;
    private String email;
    private Date birth;
    private List<Doubt> doubts;
}
