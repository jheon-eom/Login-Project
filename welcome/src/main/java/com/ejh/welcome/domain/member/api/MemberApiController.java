package com.ejh.welcome.domain.member.api;

import com.ejh.welcome.domain.member.application.MemberService;
import com.ejh.welcome.domain.member.domain.Member;
import com.ejh.welcome.domain.member.dto.JoinRequest;
import com.ejh.welcome.domain.member.dto.MemberResponse;
import com.ejh.welcome.domain.member.dto.MemberUpdateDTO;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /**
     * 회원정보
     */
    @GetMapping("/email")
    public ResponseEntity<MemberResponse> findMember() {
        String testEmail = "e4033jh@daum.net";
        return ResponseEntity.ok(memberService.findByEmail(getEmail()));
    }

    /**
     * 회원정보 수정
     */
    @PatchMapping
    public ResponseEntity<Void> update(@Valid @RequestBody MemberUpdateDTO updateDTO) throws DuplicateMemberException {
        log.debug("{} : 회원 수정", updateDTO.getEmail());
        memberService.updateMember(updateDTO.toEntity(), getEmail());
        return ResponseEntity.ok().build();
    }

    private String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
