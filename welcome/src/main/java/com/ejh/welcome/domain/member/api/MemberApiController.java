package com.ejh.welcome.domain.member.api;

import com.ejh.welcome.domain.member.application.MemberService;
import com.ejh.welcome.domain.member.domain.Member;
import com.ejh.welcome.domain.member.dto.JoinRequest;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody JoinRequest memberDTO) throws DuplicateMemberException {
        Member member = memberDTO.toEntity();
        URI uri = URI.create("/members/findByEmail");
        return ResponseEntity.created(uri).body(memberService.signUp(member));
    }

}
