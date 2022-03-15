package com.gteam.glog.mypage.controller;

import com.gteam.glog.common.utils.JWTTokenUtils;
import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.domain.dto.UserInfoDTO;
import com.gteam.glog.domain.entity.users.UsersRepository;
import com.gteam.glog.domain.enums.LoginErrorCode;
import com.gteam.glog.mypage.service.MyPageService;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Valid
@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {
    private final ResponseDTOUtils responseDTOUtils;
    private final MyPageService myPageService;


    /**
     * 유저 정보 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/myinfo/{id}")
    @ApiOperation(value = "유저 정보 조회", notes = "유저 정보를 요청하는 API")
    public ResponseEntity<?> getMyPage(@PathVariable(name = "id")Long id) {
        log.info("--> getMypage : - {}",id);
        return responseDTOUtils.doGenerateResponseDTO(myPageService.findUserInfoByUserId(id));
    }


    /**
     *유저 정보 수정
     *
     * @param userInfoDTO
     * @return
     */
    @PostMapping("/myinfo")
    @ApiOperation(value = "유저 정보 수정", notes = "유저 정보를 수정하는 API")
    public ResponseEntity<?> updateMyPage(@RequestBody()UserInfoDTO userInfoDTO) {
        log.info("--> updateMypage : - {}",userInfoDTO.getMail());
        return responseDTOUtils.doGenerateResponseDTO(myPageService.saveUserInfo(userInfoDTO));
    }

    /**
     * user not found exception
     *
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> argumentException(Exception e){
        return responseDTOUtils.doGenerateResponseDTO(e.getMessage(), LoginErrorCode.NOT_FOUND);
    }

    /**
     * Validation Exception
     *
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> nulltException(Exception e){
        return responseDTOUtils.doGenerateResponseDTO(e.getMessage(), LoginErrorCode.FAILED_VALIDATION);
    }

}
