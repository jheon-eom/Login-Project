package com.ejh.welcome.domain.auth.principal;

import com.ejh.welcome.domain.member.domain.MemberRepository;
import com.ejh.welcome.domain.member.exception.EmailNotFoundException;
import com.ejh.welcome.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws EmailNotFoundException {
        return memberRepository.findByEmail(email)
                .map(CustomUserDetails::createDetails)
                .orElseThrow(() -> new EmailNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

}
