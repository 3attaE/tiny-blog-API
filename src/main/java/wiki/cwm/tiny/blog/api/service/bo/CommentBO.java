package wiki.cwm.tiny.blog.api.service.bo;

import lombok.Data;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogComment;

@Data
public class CommentBO {

    private Long postId;

    private String comment;

    public BlogComment toDAO() {
        BlogComment dao = new BlogComment();
        dao.setPostId(postId);
        dao.setContent(comment);
        return dao;
    }
}
