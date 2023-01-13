package com.ejh.welcome.domain.member.dto;

import com.ejh.welcome.domain.member.domain.Member;
import com.ejh.welcome.domain.member.domain.RoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinRequest {

    @NotBlank(message = "필수 값입니다.")
    @Email
    @Size(min = 5, max = 30)
    private String email;

    @NotBlank(message = "필수 값입니다.")
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank(message = "필수 값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z]*$")
    @Size(min = 3, max = 10)
    private String name;

    @NotBlank(message = "필수 값입니다.")
    @Size(min = 4, max = 20)
    private String nickname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .roleType(RoleType.USER)
                .nickname(nickname)
                .birth(birth)
                .build();
    }

}
