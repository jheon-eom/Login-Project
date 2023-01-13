package com.ejh.welcome.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, length = 30)
    private String email;

    @Column(length = 120)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_type", length = 15)
    private RoleType roleType;

    @Column(unique = true, length = 20)
    private String nickname;

    @Column(length = 20)
    private String name;

    private LocalDate birth;

    private int age;

    @Builder
    protected Member(final Long id, final String email, final String password, RoleType roleType,
                     final String nickname, final String name, final LocalDate birth) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleType = roleType;
        this.nickname = nickname;
        this.name = name;
        this.birth = birth;
        this.age = addAge(birth);
    }

    private int addAge(final LocalDate birth) {
        int memberYear = birth.getYear();
        int nowYear = LocalDate.now().getYear();

        return nowYear - memberYear;
    }

}
