package com.ejh.welcome.domain.member.util;

import com.ejh.welcome.domain.member.domain.Member;
import com.ejh.welcome.domain.member.domain.RoleType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class GivenMember {

    public static final String GIVEN_EMAIL = "e4033jh@daum.net";
    public static final String GIVEN_PASSWORD = "12345678a";
    public static final String GIVEN_NICKNAME = "eomjh";
    public static final String GIVEN_NAME = "eomjh";
    public static final LocalDate GIVEN_BIRTH = LocalDate.of(1995, 6, 17);

    public static Member toEntity(final PasswordEncoder encoder) {
        return Member.builder()
                .email(GIVEN_EMAIL)
                .password(encoder.encode(GIVEN_PASSWORD))
                .nickname(GIVEN_NICKNAME)
                .name(GIVEN_NAME)
                .roleType(RoleType.USER)
                .birth(GIVEN_BIRTH)
                .build();
    }

    public static Member toEntityNoEncoder() {
        return Member.builder()
                .email(GIVEN_EMAIL)
                .password(GIVEN_PASSWORD)
                .nickname(GIVEN_NICKNAME)
                .name(GIVEN_NAME)
                .roleType(RoleType.USER)
                .birth(GIVEN_BIRTH)
                .build();
    }
}
