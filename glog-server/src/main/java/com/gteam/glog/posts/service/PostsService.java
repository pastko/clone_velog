package com.gteam.glog.posts.service;

import com.gteam.glog.domain.dto.PostsInfoDTO;
import com.gteam.glog.domain.dto.post.PostContentsDTO;
import com.gteam.glog.domain.dto.post.PostRequestDTO;
import com.gteam.glog.domain.entity.contens.Posts;
import com.gteam.glog.domain.entity.contens.PostsRepository;
import com.gteam.glog.domain.entity.users.Users;
import com.gteam.glog.domain.entity.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.DataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Log4j2
@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postRepository;
    private final UsersRepository usersRepository;

    /**
     * posts 생성
     * @param postsInfoDTO
     * @return
     */
    @Transactional
    public Long createPost(PostsInfoDTO postsInfoDTO) {
        try {
            Users users = usersRepository.findById(postsInfoDTO.getUser_idx()).orElseThrow(()->{
                throw new NullPointerException("유저가 존재하지 않습니다.");
            });
            Posts posts = Posts.initPost().users(users).postsInfoDTO(postsInfoDTO).build();
            return postRepository.save(posts).getIdx();
        }catch (Exception e){
            throw new IllegalStateException("Posts가 저장되지 않았습니다.");
        }
    }

    /**
     * 내 posts 전체 조회 ( 페이징 )
     * @param uIdx  : user idx
     * @param pageable
     * @return
     */
    public Page<PostsInfoDTO> getPosts(Long uIdx, Pageable pageable) {
        return postRepository.findPostsByUsers_Idx(uIdx,pageable).orElseThrow(()->{
            throw new NullPointerException("데이터가 존재하지 않습니다.");
        }).map(e->e.getPostDTO());
    }

    /**
     *  내 post 단일 조회
     * @param idx  : post idx
     * @return
     */
    public PostsInfoDTO detailsPost(Long idx) {
        return postRepository.findPostsByIdx(idx).orElseThrow(()->{
            throw new NullPointerException("기존 데이터가 존재하지 않습니다.");
        }).getPostDTO();
    }

    /**
     *  내 post 수정
     * @param postsInfoDTO
     * @return
     */
    @Transactional
    public Long updatePost(PostsInfoDTO postsInfoDTO){
        return postRepository.findPostsByIdx(postsInfoDTO.getIdx()).orElseThrow(()->{
            throw new NullPointerException("기존 데이터가 존재하지 않습니다.");
        }).Update(postsInfoDTO);
    }



    /**
     *  내 post 삭제
     * @param idx   : post idx
     * @return
     */
    @Transactional
    public Long deletePost(Long idx) {
        Posts posts= postRepository.findPostsByIdx(idx).orElseThrow(()->{
            throw new NullPointerException("데이터가 존재하지 않습니다.");
        });
        postRepository.delete(posts);
        return posts.getIdx();
    }
}
