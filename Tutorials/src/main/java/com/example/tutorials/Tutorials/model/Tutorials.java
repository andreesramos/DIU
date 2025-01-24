package com.example.tutorials.Tutorials.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class Tutorials {

    private String id;
    private String title;
    private String description;
    private Boolean published;

}

