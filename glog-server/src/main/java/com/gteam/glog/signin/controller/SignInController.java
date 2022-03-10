package com.gteam.glog.signin.controller;

import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.domain.dto.ReturnIdResponseDTO;
import com.gteam.glog.domain.dto.login.LoginRequestDTO;
import com.gteam.glog.signin.service.SignInService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignInController {
    private final SignInService loginService;
    private final ResponseDTOUtils responseDTOUtils;

    @PostMapping("/signin")
    @ApiOperation(value = "로그인 API", notes = "로컬 사용자 로그인 API")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDTO loginRequestDTO,
                                    HttpServletResponse response){

        log.info("Sign In : {}", loginRequestDTO.getMail());
        return responseDTOUtils.doGenerateResponseDTO(ReturnIdResponseDTO.builder()
                .id(loginService.doLogin(loginRequestDTO))
                .build());
    }

    @GetMapping("/signout/{id}")
    @ApiOperation(value = "로그인 아웃 API", notes = "사용자 로그아웃 API")
    public ResponseEntity<?> signOut(@PathVariable("id")Long id, HttpServletResponse response){
        log.info("Sign Out :");
        if(loginService.doLogOut(id)){
            return responseDTOUtils.doGenerateResponseDTO("logout Success");
        }else {
            return responseDTOUtils.doGenerateResponseDTO(null);
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Data Not Found")
    public class DataNotFoundException extends RuntimeException { }
}
