package wiki.cwm.tiny.blog.api.dto;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private Long createId;
    private Long updateId;

    private Long createTime;
    private Long updateTime;
}
