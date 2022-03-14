package com.gteam.glog.domain.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@Validated
public class LoginRequestDTO {
    @Email
    private String mail;

    @Size(min=8)
    @NotBlank
    private String pwd;
}
