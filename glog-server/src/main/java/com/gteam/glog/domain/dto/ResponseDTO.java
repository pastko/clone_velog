package com.gteam.glog.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDTO {
    private boolean success;
    private Object data;
    private String msg;

    @Builder
    public ResponseDTO(Boolean success, Object data, String msg){
        this.success = success;
        this.data = data;
        this.msg = msg;
    }
}
