package com.gteam.glog.posts.service;

import com.gteam.glog.domain.dto.post.PostContentsDTO;
import com.gteam.glog.domain.dto.post.PostRequestDTO;
import com.gteam.glog.domain.entity.contens.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postRepository;

    public Long createPost(PostRequestDTO postRequestDTO, String id) {
        return null;
    }

    public List<PostContentsDTO> updatePost(String id, int pageNum) {
        return null;
    }

    public PostContentsDTO detailsPost(int idx) {
        return null;
    }

    public String deletePost(PostRequestDTO requestDTO) {
        return null;
    }
}
