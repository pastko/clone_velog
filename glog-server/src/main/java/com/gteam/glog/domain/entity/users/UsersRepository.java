package com.gteam.glog.domain.entity.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query("Select us from Users as us where us.mail=?1")
    Optional<Users> findByMail(String mail);
    Optional<Users> findByNikNm(String nikNm);

}
