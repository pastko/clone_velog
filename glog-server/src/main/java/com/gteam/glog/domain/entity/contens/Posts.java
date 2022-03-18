package com.gteam.glog.domain.entity.contens;

import com.gteam.glog.domain.dto.PostsInfoDTO;
import com.gteam.glog.domain.entity.TimeStamp;
import com.gteam.glog.domain.entity.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "posts")
public class Posts extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`idx`")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_idx")
    private Users users;

    @Column(name = "title_name")
    private String title_nm;

    @Column(name = "title_img")
    private String title_img;

    @Column(name = "cont")
    private String contents;

    @Column(name = "tag")
    private String tag;

    @Column(name = "contHtml")
    private String html;

    @Builder(builderClassName = "initPost", builderMethodName = "initPost")
    public Posts(Users users,PostsInfoDTO postsInfoDTO) {
        this.users = users;
        this.title_nm = postsInfoDTO.getTitle_nm();
        this.title_img = postsInfoDTO.getTitle_img();
        this.contents = postsInfoDTO.getContents();
        this.tag = postsInfoDTO.getTag();
        this.html = postsInfoDTO.getHtml();
    }

    public Long Update(PostsInfoDTO postsInfoDTO){
        this.title_nm = postsInfoDTO.getTitle_nm();
        this.title_img = postsInfoDTO.getTitle_img();
        this.contents = postsInfoDTO.getContents();
        this.tag = postsInfoDTO.getTag();
        this.html = postsInfoDTO.getHtml();
        return this.idx;
    }

    public PostsInfoDTO getPostDTO(){
        return PostsInfoDTO.updatePosts()
                .idx(this.idx)
                .title_nm(this.title_nm)
                .title_img(this.title_img)
                .contents(this.contents)
                .tag(this.tag)
                .html(this.html)
                .user_idx(this.getUsers().getIdx())
                .build();
    }
}
