package com.example.d3nserver.user.domain;

import com.example.d3nserver.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User extends BaseEntity {

    @Id
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;


}
