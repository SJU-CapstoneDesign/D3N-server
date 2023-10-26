package com.example.d3nserver.user.domain;

import com.example.d3nserver.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseEntity {
    @Id
    private Long id;
    @Column(unique = true)
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer birthYear;
    @ElementCollection
    private List<String> categoryList;
    @ElementCollection
    private List<Integer> scrapList;
    private String appleRefreshToken;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private MemberProvider memberProvider;
}
