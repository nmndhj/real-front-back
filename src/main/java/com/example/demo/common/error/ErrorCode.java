package com.example.demo.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    MEMBER_NOT_FOUND("BIZ001", "존재하지 않는 유저입니다."),
    LOGIN_FAILED("BIZ002", "로그인/패스워드가 일치하지 않습니다."),
    MEMBER_IS_EXIST("BIZ003", "이미 가입된 유저입니다."),
    TARGET_DATA_NOT_FOUND("BIZ000", "수정/삭제 하고자 하는 데이터가 존재하지 않습니다."),
    DUPLICATE_RESOURCE("BIZ003", "데이터가 이미 존재합니다"),

    JWT_TOKEN_EXPIRED("JWT001", "유효기간이 만료된 토큰입니다."),
    JWT_TOKEN_NOT_SUPPORTED("JWT002", "잘못된 JWT 토큰입니다."),

    TOO_MANY_REQUEST("CIRCUIT002", "동시간 API 요청 제한 수를 초과했습니다."),

    ;

    private final String errorCode;
    private final String msg;

    ErrorCode(String errorCode, String msg){
        this.errorCode = errorCode;
        this.msg = msg;
    }
}