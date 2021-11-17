package com.gteam.glog.post.controller;

import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.domain.dto.post.PostRequestDTO;
import com.gteam.glog.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final ResponseDTOUtils responseDTOUtils;

    @Autowired
    public PostController(PostService postService, ResponseDTOUtils responseDTOUtils) {
        this.postService = postService;
        this.responseDTOUtils = responseDTOUtils;
    }
    @PostMapping("/add")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDTO requestDTO, @RequestHeader("X-USR-ID") String id, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postService.createPost(requestDTO, id));
    }
    // 내 게시물 목록 조회
    @GetMapping("/posts")
    public ResponseEntity<?> getPostsByUserId(@RequestHeader("X-USR-ID") String id, @RequestParam("pageNum") int pageNum, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postService.updatePost(id, pageNum));
    }
    @GetMapping("/detail")
    public ResponseEntity<?> detailPost(@RequestParam("idx") int idx, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postService.detailsPost(idx));
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestBody PostRequestDTO requestDTO, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(postService.deletePost(requestDTO));
    }
}
