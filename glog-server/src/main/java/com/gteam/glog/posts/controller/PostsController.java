package com.gteam.glog.posts.controller;

import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.domain.dto.post.PostRequestDTO;
import com.gteam.glog.domain.enums.ErrorCode;
import com.gteam.glog.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final ResponseDTOUtils responseDTOUtils;


    @PostMapping("/add")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDTO requestDTO, @RequestHeader("X-USR-ID") String id, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postsService.createPost(requestDTO, id));
    }
    // 내 게시물 목록 조회
    @GetMapping("/posts")
    public ResponseEntity<?> getPostsByUserId(@RequestHeader("X-USR-ID") String id, @RequestParam("pageNum") int pageNum, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postsService.updatePost(id, pageNum));
    }
    @GetMapping("/detail")
    public ResponseEntity<?> detailPost(@RequestParam("idx") int idx, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postsService.detailsPost(idx));
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestBody PostRequestDTO requestDTO, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postsService.deletePost(requestDTO));
    }

    /**
     * null exception
     *
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nulltException(Exception e){
        return responseDTOUtils.doGenerateResponseDTO(e.getMessage(), ErrorCode.NOT_FOUND);
    }
}
