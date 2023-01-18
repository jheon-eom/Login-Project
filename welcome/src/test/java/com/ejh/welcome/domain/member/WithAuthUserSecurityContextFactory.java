package com.ejh.welcome.domain.member;

import com.ejh.welcome.domain.member.domain.Member;
import com.ejh.welcome.domain.member.domain.RoleType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.LocalDate;
import java.util.List;

public class WithAuthUserSecurityContextFactory implements WithSecurityContextFactory<WithAuthUser> {

    @Override
    public SecurityContext createSecurityContext(WithAuthUser annotation) {
        Member member = Member.builder()
                .id(annotation.id())
                .email(annotation.email())
                .password(annotation.password())
                .name(annotation.name())
                .roleType(RoleType.USER)
                .nickname(annotation.nickname())
                .birth(LocalDate.of(1995, 6, 17))
                .build();

        List<GrantedAuthority> role =
                AuthorityUtils.createAuthorityList(RoleType.USER.name(), RoleType.STRANGER.name());

        SecurityContextHolder.getContext().setAuthentication
                (new UsernamePasswordAuthenticationToken(member.getEmail(), annotation.password(), role));

        return SecurityContextHolder.getContext();
    }

}
