package com.gteam.glog.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDTO {
    private int status;
    private Object data;
    private String msg;

    @Builder
    public ResponseDTO(int status, Object data, String msg){
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public ResponseDTO(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return this.data;
    }

    public void setResult(Object result) {
        this.data = result;
    }

    @Override
    public String toString() {
        return "ApiResponse [ statusCode=" + this.status + ", message=" + this.msg +" ]";
    }
}
