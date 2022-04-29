package com9.shop.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Store Error
    STORE_NOT_FOUND(404, "STORE-404-1", "해당 Id의 스토어를 찾을 수 없습니다"),

    // User Error
    USER_NOT_FOUND(401, "USER-401-1", "해당 Id를 가진 유저를 찾을 수 없습니다"),

    // 일반적인 에러
    COMMON_BAD_REQUEST(400, "COMMON-400-1", "요청한 값이 올바르지 않습니다"),
    COMMON_NOT_FOUND(404, "COMMON-404-1", "존재하지 않는 값입니다"),
    COMMON_METHOD_NOT_ALLOWED(405, "COMMON-405-1", "허용되지 않은 메소드 입니다"),
    COMMON_INTERNAL_SERVER_ERROR(500, "COMMON-500-1", "일시적인 서버 오류 입니다"),

    // REST ERROR
    REST_SERVER_ERROR(500, "REST-500-1", "통신 서버 오류입니다");

    private final int status;
    private final String code;
    private final String message;

    public String getErrorMessage(Object... arg) {
        return String.format(message, arg);
    }
}
