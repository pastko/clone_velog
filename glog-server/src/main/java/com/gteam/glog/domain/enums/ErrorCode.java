package com.gteam.glog.domain.enums;

public enum ErrorCode {


    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(5020, 400,"리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(5022, 400,"리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    FAILED_VALIDATION(5025, 400,"요청에 잘못된 데이터가 있습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    FAILED_AUTHORIZED(6000, 401, "허가 않은 요청입니다."),
    UNRECOGNIZED_ROLE(6010, 401,"Unrecognized Role"),
    INVALID_AUTH_TOKEN(6013, 401, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(6015, 401, "현재 내 계정 정보가 존재하지 않습니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    NOT_FOUND(7000, 404, "정보를 찾을 수 없습니다"),
    TOKEN_NOT_FOUND(7013, 404, "토큰 정보를 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(8000, 409, "데이터가 이미 존재합니다");


    private int code;
    private int status;
    private String description;

    private ErrorCode(int code, int status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }
    public int getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }
}
