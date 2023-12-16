package com.example.demo.common.error;

import lombok.Getter;


@Getter
public class BizException extends RuntimeException {
    ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;

    }
}
