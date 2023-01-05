package com.lefnds.doubtforum.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoubtRequestDTO {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

}
