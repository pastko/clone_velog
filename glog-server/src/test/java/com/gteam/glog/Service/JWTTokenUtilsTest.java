package com.gteam.glog.Service;

import com.gteam.glog.common.utils.JWTTokenUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class JWTTokenUtilsTest {
    private final JWTTokenUtils jwtTokenUtils;
//    JWTTokenUtils jwtTokenUtils;
    @Autowired
    JWTTokenUtilsTest(JWTTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @BeforeEach
    public void before(){
//        jwtTokenUtils = new JWTTokenUtils();
    }

    @Test
    void tokenTest() {
//        Users users = new Users();
//        users.setPwd("$2a$12$BQUaoQT9bBjbCxsZcWPUGeTWAbnwSniT5ZiNY.Uz3q/EjcTS0.dQG");
//        users.setMail("glog@gmail.com");
//
//        String tesToken = jwtTokenUtils.issuanceAccessToken(users);
//        String key = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJtYWlsIjoiZ2xvZ0BnbWFpbC5jb20iLCJwd2QiOiIiLCJzdWIiOiIkMmEkMTAkUURSTDc2Mm04bWJQWGV5M2hDZ0xaLk5rbWVYRjFzWGNJcHg3bGdTdHltUnhTbFBad01hcUsiLCJpYXQiOjE2MzY0NjA2OTYsImV4cCI6MTYzNjQ2MDg3Nn0.50lOvNwyLlqsgNERDN1iQak9K-RpKuKvqz2VR30LdFSvKGFpMpYOBkR-BI28k2AFLU2kPsZSXGQ0ClOWlzHYCg";
//
//        System.out.printf("\n\n");
//        System.out.printf("Test Token : "+tesToken);
//        System.out.printf("\n\n");
////        System.out.printf("Test user id : "+claims.get("userId"));
//        System.out.printf("\n\n");
//        Claims claims = jwtTokenUtils.getAllClaimsFromToken(tesToken);
//        System.out.println(claims.get("mail"));
//        System.out.println(claims.getId());
//        System.out.println(claims.getSubject());
//        assertThat(jwtTokenUtils.validateAccessInfoByToken("Bearer "+tesToken,users.getMail())).isEqualTo(true);
//        try {
//            assertThat(claims.get("mail")).isEqualTo(users.getMail());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    private class ServerConfig {
    }
}