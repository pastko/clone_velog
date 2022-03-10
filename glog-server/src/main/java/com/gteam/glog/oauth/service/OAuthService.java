package com.gteam.glog.oauth.service;

import com.gteam.glog.domain.dto.OAuthResponseDTO;
import com.gteam.glog.domain.dto.oauth.GitOAuthResponseDTO;
import com.gteam.glog.domain.dto.oauth.GitOAuthUserInfoResponseDTO;
import com.gteam.glog.domain.dto.oauth.GoogleOAuthResponseDTO;
import com.gteam.glog.domain.dto.oauth.GoogleOAuthUserInfoResponseDTO;
import com.gteam.glog.oauth.repository.OAuthRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class OAuthService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final GoogleSocialOAuth googleSocialOAuth;
    private final GitSocialOAuth gitSocialOAuth;
    private final OAuthRepository oAuthRepository;

    @Autowired
    public OAuthService(GoogleSocialOAuth googleSocialOAuth, GitSocialOAuth gitSocialOAuth, OAuthRepository oAuthRepository) {
        this.googleSocialOAuth = googleSocialOAuth;
        this.gitSocialOAuth = gitSocialOAuth;
        this.oAuthRepository = oAuthRepository;
    }

    /**
     * Google 로그인 확인
     * 1. Google에 Access Token요청
     * 2. Google에 Access Token을 사용하여 유저 정보 요청
     * 3. 기존 회원인지 조회하여 로그인 처리 ( 기존 회원이 아닐시 회원가입 후 로그인 처리 )
     * 4. 사용자 정보 반환
     *
     * @param code
     * @return MyPage
     */
    public OAuthResponseDTO doRequestGoogleOauth(String code){
        GoogleOAuthResponseDTO googleAccessToken = googleSocialOAuth.requestAccessToken(code).orElse(null);
        if(googleAccessToken != null) {
            GoogleOAuthUserInfoResponseDTO userInfoResponseDTO = googleSocialOAuth.requestUserProfile(googleAccessToken);
             if(googleSocialOAuth.validateUsers(userInfoResponseDTO)){
                 return OAuthResponseDTO.builder()
                         .accessToken(googleAccessToken.getAccess_token())
                         .userId(userInfoResponseDTO.getId())
                         .build();
             }
        }
        return null;
    }

    /**
     * Git 로그인 확인
     * 1. Git에 Access Token요청
     * 2. Git에 Access Token을 사용하여 유저 정보 요청
     * 3. 기존 회원인지 조회하여 로그인 처리 ( 기존 회원이 아닐시 회원가입 후 로그인 처리 )
     * 4. 사용자 정보 반환
     * @param code
     * @return MyPage
     */
    public OAuthResponseDTO doRequestgitOauth(String code){
        GitOAuthResponseDTO.AccessToken gitAccessToken = gitSocialOAuth.requestAccessToken(code).orElse(null);
        if(gitAccessToken != null) {
            GitOAuthUserInfoResponseDTO userInfoResponseDTO = gitSocialOAuth.requestUserProfile(gitAccessToken);
            if(gitSocialOAuth.validateUsers(userInfoResponseDTO)){
                return OAuthResponseDTO.builder()
                        .accessToken(gitAccessToken.getAccess_token())
                        .userId(userInfoResponseDTO.getId())
                        .build();
            }
        }
        return null;
    }

}


