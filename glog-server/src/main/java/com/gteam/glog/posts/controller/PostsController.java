package com.gteam.glog.posts.controller;

import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.domain.dto.PostsInfoDTO;
import com.gteam.glog.domain.dto.post.PostRequestDTO;
import com.gteam.glog.domain.enums.ErrorCode;
import com.gteam.glog.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Log4j2
@Validated
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final ResponseDTOUtils responseDTOUtils;

    /**
     * Posts 추가
     * @param postsInfoDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<?> createPost(@RequestBody PostsInfoDTO postsInfoDTO) {
        log.info(postsInfoDTO.toString());
        return responseDTOUtils.doGenerateResponseDTO(postsService.createPost(postsInfoDTO));
    }

    /**
     * 내 Posts 전체 조회
     * @param pageable
     * @param uIdx
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllPosts(Pageable pageable, @RequestParam("uIdx")Long uIdx) {
        log.info("Get all Posts "+pageable.getSort()+" : ");
        return responseDTOUtils.doGenerateResponseDTO(postsService.getPosts(uIdx,pageable));
    }

    /**
     * 내 Post 상세 조회
     * @param idx
     * @return
     */
    @GetMapping("/detail")
    public ResponseEntity<?> detailPost(@RequestParam("idx") Long idx) {
        log.info("Get Post by"+idx);
        return responseDTOUtils.doGenerateResponseDTO(postsService.detailsPost(idx));
    }

    /**
     * 내 post 수정
     * @param postsInfoDTO
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<?> updatePost(@RequestBody PostsInfoDTO postsInfoDTO){
        log.info(postsInfoDTO.toString());
        return responseDTOUtils.doGenerateResponseDTO(postsService.updatePost(postsInfoDTO));
    }

    /**
     *  내 post 삭제
     * @param idx
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestParam("idx") Long idx) {
        return responseDTOUtils.doGenerateResponseDTO(postsService.deletePost(idx));
    }

    /**
     * IllegalArgument Exception
     *
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> IllegalArgumentException(Exception e){
        return responseDTOUtils.doGenerateResponseDTO(e.getMessage(), ErrorCode.FAILED_VALIDATION);
    }

    /**
     * NullPointer Exception
     *
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullException(Exception e){
        return responseDTOUtils.doGenerateResponseDTO(e.getMessage(), ErrorCode.NOT_FOUND);
    }
}
