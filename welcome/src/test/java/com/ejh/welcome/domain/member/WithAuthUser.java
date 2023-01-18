package com.ejh.welcome.domain.member;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithAuthUserSecurityContextFactory.class)
public @interface WithAuthUser {

    long id() default 1L;
    String email() default "e4033jh@daum.net";
    String password() default "12345678a";
    String name() default "eomjh";
    String nickname() default "eomjh";
}
