package com.gteam.glog.domain.dto;

import io.swagger.annotations.BasicAuthDefinition;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@NoArgsConstructor
public class PostsInfoDTO {
    private Long idx;
    private String title_nm;
    @NotBlank
    private String title_img;
    private String contents;
    @NotBlank
    private String tag;
    private String html;
    @NotNull
    private Long user_idx;

    @Builder(builderClassName = "updatePosts", builderMethodName = "updatePosts")
    public PostsInfoDTO(Long idx,String title_nm, String title_img, String contents, String tag,String html,Long user_idx) {
        this.idx = idx;
        this.title_nm = title_nm;
        this.title_img = title_img;
        this.contents = contents;
        this.tag = tag;
        this.html = html;
        this.user_idx = user_idx;
    }

    @Builder(builderClassName = "createPosts", builderMethodName = "createPosts")
    public PostsInfoDTO(String title_nm, String title_img, String contents, String tag,String html,Long user_idx) {
        this.title_nm = title_nm;
        this.title_img = title_img;
        this.contents = contents;
        this.tag = tag;
        this.html = html;
        this.user_idx = user_idx;
    }


    @Override
    public String toString(){
        return "PostsDTO [ title_name = " + this.title_nm +
                ", title_img = " + this.title_img +
                ", contents = " + this.contents +
                ", tag = "+this.tag+
                ", html = "+this.html+
                ", User idx = "+this.user_idx+" ]";
    }
}
