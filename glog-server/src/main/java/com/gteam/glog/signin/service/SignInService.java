package com.gteam.glog.signin.service;

import com.gteam.glog.common.utils.JWTTokenUtils;
import com.gteam.glog.domain.dto.login.LoginRequestDTO;
import com.gteam.glog.domain.entity.users.Users;
import com.gteam.glog.domain.entity.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

@Log4j2
@Service
@RequiredArgsConstructor
public class SignInService {
    private final UsersRepository usersRepository;
    private final JWTTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 로그인
     *
     * @param loginRequestDTO
     * @return
     *       > 사용자 검증 성공 : Access Token
     *       > 사용자 검증 실패 : null
     */
    @Transactional
    public Long doLogin(LoginRequestDTO loginRequestDTO){
        Users users = usersRepository.findByMail(loginRequestDTO.getMail()).orElseThrow(() -> {
                    log.info("사용자가 존재하지 않습니다.");
                    return new IllegalArgumentException("사용자가 존재 하지 않습니다.");
                }
        );

        if (!passwordEncoder.matches(loginRequestDTO.getPwd(), users.getPwd())) {
            log.info("비밀번호 오류.");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        users.isLogin();
        return users.getIdx();
    }


    /**
     * 사용자 로그아웃
     * 1. Access Token / 사용자 검증
     * 2. Access Token 만료시 Refresh Token을 사용한 검증
     * 3. 로그아웃
     *
     * @param id - Access Token
     * @return
     *       > 사용자 검증 성공 : ture
     *       > 사용자 검증 실패 : false
     */
    @Transactional
    public Long doLogOut(Long id){
        Users users = usersRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("사용자가 존재 하지 않습니다.");
        });
        users.isLogout();
        return users.getIdx();
    }


    /**
     * Refresh Token을 Cookie로 만들어 반환
     * 1. 사용자 인증 정보의 Key로 저장되어 있는 Refresh Token 조회
     * 2. Token 만료시 & Null 시 Refresh Token 재발급 하여 DB 저장
     * 3. Refresh Token를 Cookie로 만들어 반환
     *
     * @param mail
     * @return Cookie
     */
    public Cookie getRefreshTokenToCookie(String mail){
       return new Cookie("refresh", jwtTokenUtils.getRefreshToken(mail));
    }
}
