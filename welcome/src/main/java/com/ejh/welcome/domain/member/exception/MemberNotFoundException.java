package com.ejh.welcome.domain.member.exception;

import com.ejh.welcome.global.error.exception.EntityNotFoundException;
import com.ejh.welcome.global.error.exception.ErrorCode;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
