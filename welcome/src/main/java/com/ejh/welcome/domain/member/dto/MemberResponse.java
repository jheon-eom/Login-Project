package com.ejh.welcome.domain.member.dto;

import com.ejh.welcome.domain.member.domain.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    @Email
    @Size(min = 5, max = 30)
    private String email;

    @Size(min = 3, max = 10)
    private String nickname;

    @Size(min = 4, max = 20)
    private String name;

    private int age;

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getEmail(), member.getNickname(),
                member.getName(), member.getAge());
    }

}
