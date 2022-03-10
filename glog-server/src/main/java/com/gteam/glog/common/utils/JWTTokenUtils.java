package com.gteam.glog.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gteam.glog.domain.dto.login.LoginRequestDTO;
import com.gteam.glog.domain.entity.users.Users;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.persistence.NoResultException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@Log4j2
@RequiredArgsConstructor
public class JWTTokenUtils {

    @Value("${auth.tokenSecret}")
    private String SIGN_KEY;
    @Value("${auth.subject}")
    private String SUBJECT_KEY;
    private static final long ACCESS_TOKEN_EXPIRED_TIME = 1 * 60  * 1000;       // 1분
    private static final long REFRESH_TOKEN_EXPIRED_TIME = 1 * 24 * 60 * 60 * 1000; // 1개월 => 1일로 변경
    private final ObjectMapper objectMapper;


    /**
     * jwt 토큰에서 만효되는 날짜 조회
     *
     * @param token -
     * @return
     */
    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    /**
     * secret 키를 가지고 토큰 내부 정보 조회
     *
     * @param token -
     * @return
     */
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SIGN_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * 토큰 만료 체크
     *
     * @param token -
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    /**
     * 토큰을 생성하는 동안
     * 1. 토큰, Issuer, Expiration, Subject, ID로 claims를 정의한다.
     * 2. HS512알고리즘과 secret key를 가지고 JWT를 서명한다.
     *
     * @param claims -
     * @return generater token string
     */
    private String doGenerateToken(Map<String, Object> claims,long expireTime) {
        return Jwts.builder()
                .setHeader(new HashMap<>(){{
                    put("typ","JWT");
                    put("alg","HS512");
                }})
                .setClaims(claims)
                .setSubject(SUBJECT_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, SIGN_KEY)
                .compact();
    }

    /**
     * jwt 토큰에서 유효성 검사를 위한 subject 조회
     *
     * @param token -
     * @return Token Subject values
     */
    private String getSubjectFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * Refresh Token 재발급 후 DB update
     *
     * @param users
     * @return
     */
    private void updateIssuanceRefreshToken(Users users){
//        if(users != null) {
//            String key = this.issuanceRefreshToken(users);
//            loginRepository.updateUserKey(users.getMail(), key);
//        }else{
//            log.info("updateIssuanceRefreshToken: false - {}",users.getMail());
//        }
    }

    /**
     * 헤더에 "Bearer "를 제거합니다.
     *
     * @param header -
     */
    private Boolean validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            log.info("validationAuthorizationHeader : is not contain Bearer ");
            return false;
        }
        return true;
    }
    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }

    /**
     * 토큰 유효성 검증
     * 만료 기간 및 Subject 검증
     * @param token
     * @return boolean
     */
    private Boolean validateToken(String token) {
        if(token != null) {
            final String subject = getSubjectFromToken(token);
            return (subject.equals(SUBJECT_KEY) && !isTokenExpired(token));
        }else{
            log.info("validateToken inner else : {}",token);
            return false;
        }
    }

    /**
     * 토큰에 저장되어 있는 사용자 정보를 통하여 디비 조회
     *
     * @param key
     * @return
     */
    private Boolean validateTokenForUsers(String key){
//        try {
//            Claims claims = this.getAllClaimsFromToken(key);
//            return (null != loginRepository.getUsersByUserId(claims.get("mail").toString()).orElse(null));
//        }catch (NoResultException e){
//            log.info("validateTokenForUsers --- >  : {}",e.getCause());
            return false;
//        }
    }

    /**
     * 전달된 OBJECT로 Access Token 생성
     *
     * @param users -
     * @return Token String
     */
    public String issuanceAccessToken(Users users) {
        if(users != null) {
            try {
                Map<String, Object> claims = objectMapper.convertValue(LoginRequestDTO.builder()
                        .mail(users.getMail())
                        .pwd("")
                        .build(), Map.class);
                return doGenerateToken(claims, ACCESS_TOKEN_EXPIRED_TIME);
            }catch (Exception e){
                log.info("issuanceAccessToken Error : {}",e.getCause());
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * 전달된 OBJECT로 Refresh Token 생성
     *
     * @param users
     * @return Cookie String
     */
    public String issuanceRefreshToken(Users users){
        if(users != null) {
            try {
                Map<String, Object> claims = objectMapper.convertValue(LoginRequestDTO.builder()
                        .mail(users.getMail())
                        .pwd("")
                        .build(), Map.class);
                return doGenerateToken(claims, REFRESH_TOKEN_EXPIRED_TIME);
            }catch (Exception e){
                log.info("issuanceRefreshToken Error : {}",e.getCause());
                return null;
            }
        }else{
            return null;
        }
    }


    /**
     * 리프레쉬 토큰을 사용하여 Access 토큰 재발 급
     *
     * @param refreshKey
     * @return AccessToken
     */
    public String reissuanceAccessToken(String refreshKey){
//        if(refreshKey != null){
//            Claims claims = this.getAllClaimsFromToken(refreshKey);
//            if( claims != null) {
//                return this.issuanceAccessToken(loginRepository.getUsersByUserId(claims.get("mail").toString()).orElse(null));
//            }else{
//                log.info("Cliams is null :  False");
//                return null;
//            }
//        }else{
//            log.info("Access Token reissuance : False - {}",refreshKey);
            return null;
//        }
    }
    /**
     * 리프레쉬 토큰을 재발 급
     * 1. 재발급 시기
     *    - 첫 로그인 시  ( DB상 key( Refresh Token) 이 존재 하지 않을때 )
     *    - Refresh Token 만료 되었을 때
     * @param mail
     * @return AccessToken
     */
    public void reissuanceRefreshToken(String mail){
//        if(mail != null) {
//            this.updateIssuanceRefreshToken(loginRepository.getUsersByUserId(mail).orElse(null));
//        }else{
//            log.info("Refresh Token reissuance : False - {}",mail);
//        }
    }

    /**
     * 사용자 Access Token 검증
     * 1. 토큰 내 Bearer 문자열 검사 ( Bearer 제거 )
     * 2. 인증서 유효성 검사 ( 만료 & Subject 검사 )
     *
     * @param key
     * @return
     *        >  인증서가 유효함 : true
     *        >  인증서가 유효하지 않음 : false
     */
    public Boolean validateAccessInfoByToken(String key){
        try {
            // Bearer 제거
            if(!this.validationAuthorizationHeader(key)){
                log.info("토큰이 존재 하지 않습니다.");
                return false;
            }
            key = this.extractToken(key);
            return (this.validateToken(key) && this.validateTokenForUsers(key));
        }catch (IllegalArgumentException e){
            log.info("토큰이 유효하지 않습니다.");
            return false;
        }catch (ExpiredJwtException e){
            log.info("토큰이 만로 되었습니다.");
            return false;
        } catch(NoResultException e) {
            log.info("토큰이 존재하지 않습니다.");
            return false;
        }
    }
    /**
     * Refresh Token을 재발급하여 반환
     * 1. 저장되어 있는 Refresh Token 조회
     * 2. 조회한 token과 key값 비교
     *
     * @param key - Cookie에 저장된 Refresh Token
     * @return
     *       > Refresh Token 검증 성공 : ture
     *       > 검증 실패 (만료 & key not equals) : false
     */
    public Boolean validateRefreshToken(String key){
        try{
            return (this.validateToken(key) && this.validateTokenForUsers(key));
        }catch (NoResultException e){
            log.info("validateRefreshToken : false - {}",e.getCause());
            return false;
        }
    }

    /**
     * 토큰에 저장되어 있는 사용자 정보 조회
     * @param key
     * @return
     */
    public String getUserInfoToToken(String key){
        return this.getAllClaimsFromToken(key).get("mail").toString();
    }





    /**
     * 저장되어 있는 사용자 Refresh Token 조회
     *
     * @param mail
     * @return
     */
    public String getRefreshToken(String mail){
//        if(mail != null) {
//            return loginRepository.getUsersByUserId(mail).orElse(null).getKey();
//        }else{
//            log.info("getRefreshToken : false {}",mail);
            return null;
//        }
    }
}
