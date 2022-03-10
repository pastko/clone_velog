package com.gteam.glog.comment.repository;

import com.gteam.glog.domain.dto.comment.CommentContentsDTO;
import com.gteam.glog.domain.entity.*;
import com.gteam.glog.domain.entity.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CommentRepository {

    private final EntityManager entityManager;

    @Autowired
    public CommentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String createComment(CommentContentsDTO commentContentsDTO, String id) {
//        try {
//            Commnet commnet = new Commnet();
//            Comt_Contents contents = new Comt_Contents();
//
//            String query = "select b from bord b where bord_idx=" + commentContentsDTO.getBord_idx();
//            Board board = (Board) entityManager.createQuery(query).getResultList().get(0);
//            query = "select u from usr u where mail=" + id;
//            Users users = (Users) entityManager.createQuery(query);
//            query = "select m from mypg m where usr_idx=" + users.getIdx();
//            Mypage mypage = (Mypage) entityManager.createQuery(query);
//
//            commnet.setBord_idx(board);
//            contents.setComt_idx(commnet);
//            contents.setCret_dt(Date.valueOf(commentContentsDTO.getCreateDt()));
//            contents.setUsr_idx(mypage);
//            contents.setContents(commentContentsDTO.getContent());
//
//            entityManager.persist(commnet);
//            entityManager.persist(contents);
//
//            entityManager.flush();
//            entityManager.close();
//
//            return "ok";
//        } catch (Exception e) {
            return null;
//        }
    }

    public CommentContentsDTO updateComment(int comt_idx) {
//        try {
//            CommentContentsDTO result = new CommentContentsDTO();
//
//            String query = "select c from comt_cont c where comt_idx=" + comt_idx;
//            Comt_Contents contents = (Comt_Contents) entityManager.createQuery(query);
//            query = "select m from mypg m where usr_idx=" + contents.getUsr_idx();
//            Mypage mypage = (Mypage) entityManager.createQuery(query);
//
//            result.setContent(contents.getContents());
//            result.setNikNm(mypage.getNikNm());
//            result.setCreateDt(contents.getCret_dt().toLocalDate());
//
//            return result;
//        } catch (Exception e){
            return null;
//        }
    }

    public List<CommentContentsDTO> detailComment(int bord_idx) {
//        try {
//            List<CommentContentsDTO> contentsDTOList = new ArrayList<>();
//            String query = "select c from comt c where bord_idx=" + bord_idx;
//            List<Comt_Contents> resultList = entityManager.createQuery(query).getResultList();
//
//            for(int i = 0; i < resultList.size(); i++) {
//                CommentContentsDTO tmpValue = new CommentContentsDTO();
//
//                tmpValue.setCreateDt(resultList.get(i).getCret_dt().toLocalDate());
//                tmpValue.setContent(resultList.get(i).getContents());
//                query = "select m from mypg m where usr_idx=" + resultList.get(i).getUsr_idx();
//                Mypage mypage = (Mypage) entityManager.createQuery(query);
//                tmpValue.setNikNm(mypage.getNikNm());
//
//                contentsDTOList.add(tmpValue);
//            }
//            return contentsDTOList;
//        } catch (Exception e){
            return null;
//        }
    }

    public String deleteComment(int comt_idx) {
//        try {
//            String query = "select c from comt c where comt_idx=" + comt_idx;
//            Commnet commnet = (Commnet) entityManager.createQuery(query);
//
//            entityManager.remove(commnet);
//
//            entityManager.flush();
//            entityManager.close();
//
//            return "ok";
//        } catch (Exception e){
            return null;
//        }
    }

}
