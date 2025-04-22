package kr.co.oldcoinback.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    OK("LIME_200", "ok"),
    BAD_REQUEST("LIME_400", "bad request"),
    UNAUTHORIZED("LIME_401", "unauthorized"),
    FORBIDDEN("LIME_403", "forbidden"),
    NOT_FOUND("LIME_404", "not found"),
    INTERNAL_SERVER_ERROR("LIME_500", "관리자에게 문의 하십시오"),

    USER_NOT_FOUND("LIME_4010", "사용자를 찾을 수 없습니다.")

    ;

    final String code;
    final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
