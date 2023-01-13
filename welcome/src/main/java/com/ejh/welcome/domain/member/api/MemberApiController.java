package com.ejh.welcome.domain.member.api;

import com.ejh.welcome.domain.member.application.MemberService;
import com.ejh.welcome.domain.member.domain.Member;
import com.ejh.welcome.domain.member.dto.JoinRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public ResponseEntity<String> register(@Valid @RequestBody final JoinRequest memberDTO) {
        Member member = memberDTO.toEntity();

        return null;
    }

}
