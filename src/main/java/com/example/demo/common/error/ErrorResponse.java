package com.example.demo.common.error;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ErrorResponse {
    private final String errorCode;                     //에러 코드
    private final String message;                       //에러 메시지

    /**
     * 에러 메시지.
     * @param errCode    에러코드
     * @param message    에러 메시지(클라이언트에 출력될 메시지)
     */
    public ErrorResponse(String errCode, String message) {
        this.errorCode = errCode;
        this.message = message;
    }

    public ErrorResponse(ErrorCode errorCode){
        this.errorCode = errorCode.getErrorCode();
        this.message = errorCode.getMsg();
    }
}