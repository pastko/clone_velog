package com.gteam.glog.domain.entity.contens;

import com.gteam.glog.domain.entity.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Long> {
    Optional<Posts> findContentsByIdx(Long id);
    Optional<Posts> findContentsByUsers(Users users);
    Optional<Posts> findContentsByUsers(Users users, Sort sort);
    Optional<Posts> findContentsByUsers(Users users,Pageable pageable);
    Page<Posts> findAll(Pageable pageable);
}
