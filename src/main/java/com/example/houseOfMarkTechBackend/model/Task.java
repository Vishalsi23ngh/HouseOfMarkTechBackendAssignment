package com.example.houseOfMarkTechBackend.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    private long id;
    private String username;
    private String description;
    private boolean completed;

}
