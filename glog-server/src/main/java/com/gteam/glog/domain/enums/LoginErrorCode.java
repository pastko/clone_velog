package com.gteam.glog.domain.enums;

public enum LoginErrorCode {
    OperationNotAuthorized(6000, 200, "Operation not authorized"),
    DuplicateIdFound(6001, 401,"Duplicate Id"),
    //...
    UnrecognizedRole(6010, 402,"Unrecognized Role");
    private int code;
    private int status;
    private String description;

    private LoginErrorCode(int code, int status, String description) {
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
