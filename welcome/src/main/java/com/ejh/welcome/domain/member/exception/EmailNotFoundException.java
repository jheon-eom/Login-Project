package com.ejh.welcome.domain.member.exception;

import com.ejh.welcome.global.error.exception.EntityNotFoundException;
import com.ejh.welcome.global.error.exception.ErrorCode;

public class EmailNotFoundException extends EntityNotFoundException {

    public EmailNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
