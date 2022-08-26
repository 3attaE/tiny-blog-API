package wiki.cwm.tiny.blog.api.dao.mysql.entity;

import lombok.Data;

/**
 * 博客用户表
 */
@Data
public class BlogUser {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;
}