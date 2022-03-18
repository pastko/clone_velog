package com.gteam.glog.domain.entity.contens;

import com.gteam.glog.domain.dto.PostsInfoDTO;
import com.gteam.glog.domain.entity.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Long> {
    Optional<List<Posts>> findPostsByUsers(Users users);
    Optional<Posts> findPostsByIdx(Long id);
    Optional<List<Posts>> findPostsByUsers(Users users, Sort sort);
    Optional<Page<Posts>> findPostsByUsers_Idx(Long idx, Pageable pageable);
    Page<Posts> findAll(Pageable pageable);
}
