package wiki.cwm.tiny.blog.api.dao.mysql.entity;

import java.util.Date;

import lombok.Data;

/**
 * 博客评论表
 */
@Data
public class BlogComment {
    /**
     * 评论id
     */
    private Long id;

    /**
     * 文章ID
     */
    private Long postId;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;
}
