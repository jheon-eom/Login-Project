package com.ejh.welcome.global.error.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
