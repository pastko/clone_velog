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

    @ManyToOne
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

    @Builder(builderClassName = "initContens", builderMethodName = "initContens")
    public Posts(Users users, String title_nm, String title_img, String contents, String tag, String html) {
        this.users = users;
        this.title_nm = title_nm;
        this.title_img = title_img;
        this.contents = contents;
        this.tag = tag;
        this.html = html;
    }

    public Long Update(PostsInfoDTO postsInfoDTO){
        this.title_nm = postsInfoDTO.getTitle_nm();
        this.title_img = postsInfoDTO.getTitle_img();
        this.contents = postsInfoDTO.getContents();
        this.tag = postsInfoDTO.getTag();
        return this.idx;
    }

}
