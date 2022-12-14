package wiki.cwm.tiny.blog.api.service.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;
import wiki.cwm.tiny.blog.api.dto.PostDTO;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
public class PostBO {

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
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public PostBO(BlogPost blogPost) {
        this.setId(blogPost.getId());
        this.setTitle(blogPost.getTitle());
        this.setContent(blogPost.getContent());
        this.setStarNum(blogPost.getStarNum());
        this.setCreateId(blogPost.getCreateId());
        this.setUpdateId(blogPost.getUpdateId());
        this.setCreateTime(blogPost.getCreateTime().toInstant().getEpochSecond());
        this.setUpdateTime(blogPost.getUpdateTime().toInstant().getEpochSecond());
    }

    public PostBO(PostDTO postDTO) {
        if (Objects.nonNull(postDTO.getId())) {
            this.setId(postDTO.getId());
        }
        this.setTitle(postDTO.getTitle());
        this.setContent(postDTO.getContent());
        this.setCreateId(postDTO.getCreateId());
        this.setUpdateId(postDTO.getUpdateId());
    }

    public BlogPost toDAO() {
        BlogPost blogPost = new BlogPost();
        if (Objects.nonNull(this.id)) {
            blogPost.setId(this.id);
        }
        blogPost.setTitle(this.title);
        blogPost.setContent(this.content);
        if (Objects.nonNull(this.starNum)) {
            blogPost.setStarNum(this.starNum);
        }
        if (Objects.nonNull(this.createId)) {
            blogPost.setCreateId(this.createId);
        }
        if (Objects.nonNull(this.updateId)) {
            blogPost.setUpdateId(this.updateId);
        }
        return blogPost;
    }

    public PostDTO toDTO() {
        PostDTO dto = new PostDTO();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setContent(this.getContent());
        dto.setCreateId(this.createId);
        dto.setUpdateId(this.updateId);
        dto.setCreateTime(this.createTime);
        dto.setUpdateTime(this.updateTime);
        return dto;
    }

}
