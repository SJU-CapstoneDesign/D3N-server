package com.example.d3nserver.user.domain;

import com.example.d3nserver.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Integer birthYear;
    @ElementCollection
    private List<String> categoryList;
    @ElementCollection
    private List<Integer> scrapList;
    private String appleRefreshToken;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private MemberProvider memberProvider;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

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
