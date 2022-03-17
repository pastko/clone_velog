package com.gteam.glog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsInfoDTO {
    private Long idx;
    private String title_nm;
    private String title_img;
    private String contents;
    private String tag;
    private String html;

    @Builder
    public PostsInfoDTO(String title_nm, String title_img, String contents, String tag,String html) {
        this.title_nm = title_nm;
        this.title_img = title_img;
        this.contents = contents;
        this.tag = tag;
        this.html = html;
    }
}
