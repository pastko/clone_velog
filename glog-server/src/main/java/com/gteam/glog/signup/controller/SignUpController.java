package com.gteam.glog.signup.controller;

import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.domain.dto.SignUpRequestDTO;
import com.gteam.glog.domain.enums.ErrorCode;
import com.gteam.glog.signup.service.SignUpService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Log4j2
@Valid
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService registerService;
    private final ResponseDTOUtils responseDTOUtils;

    @PostMapping(value = "/signup")
    @ApiOperation(value = "회원가입 API", notes = "로컬 사용자 회원가입 API")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO signUp) {
        log.info("sinup - {}",signUp.getMail());
        return responseDTOUtils.doGenerateResponseDTO(registerService.createUser(signUp));
    }

    @GetMapping(value = "/signdrop/{id}")
    @ApiOperation(value = "회원탈퇴 API", notes = "로컬 사용자 회원탈퇴 API")
    public ResponseEntity<?> signDrop(@NotNull @PathVariable(name = "id") Long id) {
        log.info("sindrop - {}",id);
        return responseDTOUtils.doGenerateResponseDTO(registerService.unRegistUser(id));
    }


    /**
     * user not found exception
     *
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> duplicationException(Exception e){
        return responseDTOUtils.doGenerateResponseDTO(e.getMessage(), ErrorCode.DUPLICATE_RESOURCE);
    }

    /**
     * null exception
     *
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> nulltException(Exception e){
        return responseDTOUtils.doGenerateResponseDTO(e.getMessage(), ErrorCode.FAILED_VALIDATION);
    }
}