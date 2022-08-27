package wiki.cwm.tiny.blog.api.service.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogComment;
import wiki.cwm.tiny.blog.api.dto.CommentDTO;

@Data
@NoArgsConstructor
public class CommentBO {

    private Long postId;

    private String comment;


    public CommentBO(CommentDTO dto) {
        this.postId = dto.getPostId();
        this.comment = dto.getContent();
    }

    public BlogComment toDAO() {
        BlogComment dao = new BlogComment();
        dao.setPostId(postId);
        dao.setContent(comment);
        return dao;
    }
}
