package wiki.cwm.tiny.blog.api.dao.mysql.entity;

import java.util.Date;

import lombok.Data;

/**
 * 博客文章表
 */
@Data
public class BlogPost {
    /**
     * 文章id
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer starNum;

    /**
     * 发布者
     */
    private Long createId;

    /**
     * 发布者
     */
    private Long updateId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
