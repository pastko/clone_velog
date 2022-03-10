package com.gteam.glog.mypage.service;

import com.gteam.glog.common.utils.JWTTokenUtils;
import com.gteam.glog.domain.dto.ReturnIdResponseDTO;
import com.gteam.glog.domain.dto.UserInfoDTO;
import com.gteam.glog.domain.entity.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final JWTTokenUtils jwtTokenUtils;
    private final UsersRepository usersRepository;

    /**
     *  유저 개인 정보 조회 Id
     *  User Nik-Name, user Img-name, user glog Title-name
     *
     * 1. 사용자 검증 ( Access Token )
     * 2. 정보 조회
     *
     * @param id - Access Token
     * @return :
     *          >  userId is exist       - return user info
     *          >  userId is not exist   - return null
     */
    public UserInfoDTO findUserInfoByUserId(Long id){
        return usersRepository.findById(id).stream()
                .map(entity ->
                    UserInfoDTO.builder()
                            .mail(entity.getMail())
                            .pwd("")
                            .nikNm(entity.getNikNm())
                            .imgNm(entity.getImgNm())
                            .glogTitle(entity.getGlogTitle())
                            .build()
        ).findAny().orElse(null);
    }



    /**
     *  유저 개인 정보 수정
     *  User Nik-Name, user Img-name, user glog Title-name
     *
     *  1. 사용자 검증 ( Access Token )
     *  2. 정보 수정
     *
     * @param userInfoDTO - userinfo id
     * @return :
     *          >  userId is exist       - return user info
     *          >  userId is not exist   - return null
     */
    @Transactional
    public ReturnIdResponseDTO saveUserInfo(UserInfoDTO userInfoDTO){
        return ReturnIdResponseDTO.builder().id(usersRepository.findByMail(userInfoDTO.getMail())
                .stream()
                .map(entity-> entity.update(userInfoDTO))
                .findAny()
                .orElse(null))
                .build();
    }
}
