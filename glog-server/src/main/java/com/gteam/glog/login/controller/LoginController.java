package com.gteam.glog.login.controller;

import com.gteam.glog.domain.dto.OAuthRequestDTO;
import com.gteam.glog.domain.dto.UserAuthDTO;
import com.gteam.glog.domain.entity.Users;
import com.gteam.glog.login.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {
    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signin")
    @ApiOperation(value = "로그인 API", notes = "로컬 사용자 로그인 API")
    public ResponseEntity<?> singin(@RequestBody() UserAuthDTO userAuthDTO){
        try{
            System.out.println(userAuthDTO.getUserId());
            Optional<Users> users = loginService.validateUserLogin(userAuthDTO);
            return ResponseEntity.ok().body(loginService.doGenerateResponseDTO(users.get(),"Login success"));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(loginService.doGenerateBadResponseDTO("Login Failed"));
        }
    }

    @PostMapping("/myinfo")
    @ApiOperation(value = "유저 정보 조회", notes = "유저 정보를 요청하는 API")
    public ResponseEntity<?> getMypage(@RequestBody()UserAuthDTO userAuthDTO){
        try{
            Optional<Users> users = loginService.validateUserLogin(userAuthDTO);
            return ResponseEntity.ok().body(loginService.doGenerateResponseDTO(users.get(),"MyPage Load Success"));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(loginService.doGenerateBadResponseDTO("MyPage Load Failed"));
        }
    }

    @PostMapping("/oauth")
    @ApiOperation(value = "소셜 로그인 API", notes = "소셜 사용자 로그인 API { }")
    public ResponseEntity<?> OAuthSigin(@RequestBody() OAuthRequestDTO authorization){
        try{
            return ResponseEntity.ok().body("");
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Not found!");
        }
    }
}