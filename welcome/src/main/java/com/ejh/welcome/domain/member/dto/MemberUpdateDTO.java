package com.ejh.welcome.domain.member.dto;

import com.ejh.welcome.domain.member.domain.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDTO {

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "필수 값입니다.")
    @Size(min = 10, max = 30)
    private String email;

    @NotBlank(message = "필수 값입니다.")
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank(message = "필수 값입니다.")
    @Size(min = 4, max = 20)
    private String nickname;

    public Member toEntity() {
        return Member.createMember(email, password, nickname);
    }

}
