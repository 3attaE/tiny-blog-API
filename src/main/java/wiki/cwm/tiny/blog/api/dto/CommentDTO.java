package wiki.cwm.tiny.blog.api.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private Long postId;
    private String content;

}
