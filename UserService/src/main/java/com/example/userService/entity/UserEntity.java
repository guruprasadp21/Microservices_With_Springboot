package com.example.userService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserEntity {

    @Id
    private String userId;
    private String userName;
    private String email;
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
