package com.gteam.glog.oauth.service;

import com.gteam.glog.domain.dto.UserInfoDTO;
import com.gteam.glog.domain.dto.oauth.GoogleOAuthUserInfoResponseDTO;
import com.gteam.glog.domain.enums.SocialLoginType;
import com.gteam.glog.domain.dto.oauth.GoogleOAuthRequestDTO;
import com.gteam.glog.domain.dto.oauth.GoogleOAuthResponseDTO;
import com.gteam.glog.domain.entity.OAuthInfo;
import com.gteam.glog.oauth.repository.OAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class GoogleSocialOAuth implements SocialOAuth {
    @Value("oauth.redirectionRootUrl")
    private String redirectionRootUrl;
    private OkHttpClient okHttpClient;
    private RestTemplate restTemplate;
    private final OAuthRepository oAuthRepository;


    /**
     * google Authorization 해더 반환
     *
     * @param token
     * @return Authorization 해더
     */
    @Override
    public HttpEntity createHeaders(String token) {
        String authHeader = "Bearer " + token;
        return new HttpEntity(new HttpHeaders(){{
            set("Content-Type","application/json; charset=UTF-8");
            set("Authorization", authHeader);
        }});
    }

    /**
     * Google OAuth Request Access Token
     * 사용자 Access Code를 사용하여 Access Token 발급
     *
     * @param code
     * @return Optional<GoogleOAuthResponseDTO>
     */
    @Override
    public Optional<GoogleOAuthResponseDTO> requestAccessToken(String code) {
        try {
            OAuthInfo oAuthInfo = oAuthRepository.FindUserOAuthCode(SocialLoginType.GOOGLE).get();
            GoogleOAuthRequestDTO.AccessToken googleOAuthRequestDTO = new GoogleOAuthRequestDTO.AccessToken(
                    oAuthInfo.getClientId(),
                    oAuthInfo.getClientSecret(),
                    code,
                    "authoriztion_code",
                    redirectionRootUrl+"/google/callback"
            );

            // TODO: restTemplate => okHttp2 로 변경 해보기
            // send data id, secret key , access code , grant type, redirect url
                return Optional.of(restTemplate.postForObject(
                        oAuthInfo.getTokenUrl(),
                        googleOAuthRequestDTO,
                        GoogleOAuthResponseDTO.class));
        } catch (IllegalArgumentException e){
            log.error("Null Exception : "+ e.getMessage());
            return null;
        } catch ( HttpStatusCodeException e){
            log.error("Http Status Exception : " + e.getMessage());
            return null;
        }
    }


    /**
     * 사용자 프로필 요청
     * 1. Access Token을 활용하여 Google에 사용자 정보 조회
     *
     * @param oAuthResponseDTO
     * @return GoogleOAuthUserInfoResponseDTO
     */
    public GoogleOAuthUserInfoResponseDTO requestUserProfile(GoogleOAuthResponseDTO oAuthResponseDTO) {
        try{
            OAuthInfo oAuthInfo = oAuthRepository.FindUserOAuthCode(SocialLoginType.GOOGLE).get();
            ResponseEntity<GoogleOAuthUserInfoResponseDTO> response =  restTemplate.exchange(
                    oAuthInfo.getProfileUrl(),
                    HttpMethod.GET,
                    this.createHeaders(oAuthResponseDTO.getAccess_token()),
                    GoogleOAuthUserInfoResponseDTO.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("GoogleOauth request success");
                return response.getBody();
            } else {
                log.info("GoogleOauth request Failed");
                return null;
            }
        }catch (IllegalArgumentException e) {
            return null;
        }catch (HttpStatusCodeException e){
            return null;
        }
    }

    /**
     * 사용자 검증
     * 1. 기존 사용자인지 확인
     * 2. 기존사용자일시 사용자 정보 반환
     * 3. 신규 사용자일시 회원가입
     *
     * @param userInfoResponseDTO
     * @return MyPage
     */
    public Boolean validateUsers(GoogleOAuthUserInfoResponseDTO userInfoResponseDTO) {
//        if (userInfoResponseDTO != null){
//            if (loginRepository.getUsersByUserId(userInfoResponseDTO.getId()).orElse(null).equals(null)) {
//                this.saveGoogleUser(userInfoResponseDTO);
//            }
//            return true;
//        }else {
            return false;
//        }
    }

    /**
     * 구글 OAuth 신규 가입
     *
     * @param googleOauthUser
     */
    public void saveGoogleUser(GoogleOAuthUserInfoResponseDTO googleOauthUser){
//        registerRepository.createOatuhUserInfo(UserInfoDTO.builder()
//                        .mail(googleOauthUser.getId())
//                        .pwd("")
//                        .nikNm(googleOauthUser.getName())
//                        .glogTitle(googleOauthUser.getGiven_name()+".log")
//                        .imgNm(googleOauthUser.getPicture())
//                .build());
    }
}
