package com.ganzi.soccerhub.common.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    DUPLICATE_USER_ID(HttpStatus.BAD_REQUEST, "아이디 중복"),
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터가 포함되어 있습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    DUPLICATE_TEAM_NAME(HttpStatus.BAD_REQUEST, "팀명 중복");


    private final HttpStatus status;
    private final String message;

    public String getCode() {
        return this.name();
    }
}
