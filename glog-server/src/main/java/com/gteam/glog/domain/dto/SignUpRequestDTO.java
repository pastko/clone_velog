package com.gteam.glog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class SignUpRequestDTO {
    @Email
    private String mail;
    @NotBlank
    private String pwd;
    private String nikNm;
}
