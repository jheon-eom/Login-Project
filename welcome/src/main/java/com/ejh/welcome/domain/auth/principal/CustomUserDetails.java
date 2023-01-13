package com.ejh.welcome.domain.auth.principal;

import com.ejh.welcome.domain.member.domain.Member;
import com.ejh.welcome.domain.member.domain.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {


//    private final Long serialVersionUID = 1L;

    private final String email;
    private final String password;
    private final RoleType role;

    public CustomUserDetails(String email, String password, RoleType role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public RoleType getRole() {
        return this.role;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static CustomUserDetails createDetails(Member member) {
        return new CustomUserDetails(member.getEmail(), member.getPassword(), member.getRoleType());
    }

}
