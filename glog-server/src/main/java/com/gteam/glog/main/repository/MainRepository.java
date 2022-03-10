package com.gteam.glog.main.repository;

import com.gteam.glog.domain.dto.post.PostContentsDTO;
import com.gteam.glog.domain.entity.Contents;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class MainRepository {
    private EntityManager entityManager;
    private int pageSize;
    public MainRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    /*
    * sortRule: 1. 최신순 2. 과거순
    * */
    public List<PostContentsDTO> CallBoardData(int pageNum, int sortRule) {

        List<Contents> resultList = this.getPageResult(pageNum, sortRule);
        List<PostContentsDTO> postContentsDTOList = new ArrayList<>();

        for(int i = 0; i < resultList.size(); i++) {
            PostContentsDTO postContentsDTO = new PostContentsDTO();

            postContentsDTO.setIdx(resultList.get(i).getBoard().getIdx());
//            postContentsDTO.setNikNm(resultList.get(i).getBoard().getUsr_idx().getNikNm());
            postContentsDTO.setTitle(resultList.get(i).getTitle());
            postContentsDTO.setImgNm(resultList.get(i).getImg_nm());
            postContentsDTO.setContents(resultList.get(i).getContents());
            postContentsDTO.setCreateDt(resultList.get(i).getCreateDt());

            postContentsDTOList.add(postContentsDTO);
        }

        return postContentsDTOList;
    }

    public List<Contents> getPageResult(int pageNum, int sortRule) {
        switch (sortRule) {
            case 1:
                return entityManager.createQuery("select b from Contents as b",Contents.class)
                        .setFirstResult(pageNum * 20)
                        .setMaxResults(pageNum * 20 + 20)
                        .getResultList();
            case 2:
                return entityManager.createQuery("select b from Contents as b order by b.createDt desc",Contents.class)
                        .setFirstResult(pageNum * 20)
                        .setMaxResults(pageNum * 20 + 20)
                        .getResultList();
            default:
                entityManager.close();
                return null;
        }
    }
}
