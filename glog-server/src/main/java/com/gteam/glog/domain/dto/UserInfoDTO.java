package com.gteam.glog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDTO {
    private int usrIdx;
    private String mail;
    private String pwd;
    private String nikNm;
    private String imgNm;
    private String glogTitle;

    @Builder
    public UserInfoDTO(String mail, String pwd, String nikNm, String imgNm, String glogTitle){
        this.mail = mail;
        this.pwd = pwd;
        this.nikNm = nikNm;
        this.imgNm = imgNm;
        this.glogTitle = glogTitle;
    }
}
