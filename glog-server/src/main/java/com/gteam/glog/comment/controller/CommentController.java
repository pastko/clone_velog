package com.gteam.glog.comment.controller;

import com.gteam.glog.comment.service.CommentService;
import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.domain.dto.comment.CommentRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Log4j2
@RequestMapping("/comt")
public class CommentController {

    private final CommentService commentService;
    private final ResponseDTOUtils responseDTOUtils;

    @Autowired
    public CommentController(CommentService commentService, ResponseDTOUtils responseDTOUtils) {
        this.commentService = commentService;
        this.responseDTOUtils = responseDTOUtils;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDTO request ,@RequestHeader("X-USR-ID") String id, HttpServletResponse response) {
        return responseDTOUtils.doGenerateResponseDTO(commentService.createComment(request, id));
    }
    @GetMapping("/update")
    public ResponseEntity<?> updateComment(@RequestParam("comt_idx") int comt_idx) {
        return responseDTOUtils.doGenerateResponseDTO(commentService.updateComment(comt_idx));
    }
    @GetMapping("/detail")
    public ResponseEntity<?> detailComment(@RequestParam("bord_idx") int bord_idx) {
        return responseDTOUtils.doGenerateResponseDTO(commentService.detailComment(bord_idx));
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestBody int comt_idx) {
        return responseDTOUtils.doGenerateResponseDTO(commentService.deleteComment(comt_idx));
    }
}
