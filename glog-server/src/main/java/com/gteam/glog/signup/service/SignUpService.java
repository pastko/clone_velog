package com.gteam.glog.signup.service;

import com.gteam.glog.common.utils.JWTTokenUtils;
import com.gteam.glog.domain.dto.ReturnIdResponseDTO;
import com.gteam.glog.domain.dto.SignUpRequestDTO;
import com.gteam.glog.domain.entity.users.Users;
import com.gteam.glog.domain.entity.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SignUpService {
    private final JWTTokenUtils jwtTokenUtils;
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Long saveUser(Users users){
        return usersRepository.save(users).getIdx();
    }

    @Transactional
    public ReturnIdResponseDTO createUser(SignUpRequestDTO userInfoDTO) {
        if(usersRepository.findByMail(userInfoDTO.getMail()).isPresent()){
            throw new DuplicateKeyException("동일한 아이디가 존재 합니다.");
        }else{
            Users users = Users.initUsers()
                    .mail(userInfoDTO.getMail())
                    .pwd(passwordEncoder.encode(userInfoDTO.getPwd()))
                    .nikNm(userInfoDTO.getNikNm())
                    .build();
            return ReturnIdResponseDTO.builder().id(saveUser(users)).build();
        }
    }

    @Transactional
    public ReturnIdResponseDTO unRegistUser(Long id) {
        if(usersRepository.findById(id).isPresent()){
            usersRepository.deleteById(id);
            return ReturnIdResponseDTO.builder().id(id).build();
        }else {
            return ReturnIdResponseDTO.builder().id(null).build();
        }
    }
}
