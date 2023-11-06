package com.example.d3nserver.user.domain;

import com.example.d3nserver.common.base.BaseEntity;
import com.example.d3nserver.news.domain.Field;
import com.example.d3nserver.quiz.domain.SolvedQuiz;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    private String id;
    @Column(unique = true)
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Long birthYear;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Field> categoryList = new ArrayList<>();
    @ElementCollection
    private List<Integer> scrapList = new ArrayList<>();
    private String appleRefreshToken;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private MemberProvider memberProvider;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SolvedQuiz> solvedQuizList = new ArrayList<>();

    @Builder
    public User(String id, String appleRefreshToken, String refreshToken, MemberProvider memberProvider, RoleType roleType) {
        this.id = id;
        this.appleRefreshToken = appleRefreshToken;
        this.refreshToken = refreshToken;
        this.memberProvider = memberProvider;
        this.roleType = roleType;
    }
    @Builder
    public User(String id, RoleType roleType) {
        this.id = id;
        this.roleType = roleType;
    }

}
