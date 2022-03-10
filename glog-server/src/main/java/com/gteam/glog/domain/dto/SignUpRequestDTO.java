package com.gteam.glog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequestDTO {
    private String mail;
    private String pwd;
    private String nikNm;
}
