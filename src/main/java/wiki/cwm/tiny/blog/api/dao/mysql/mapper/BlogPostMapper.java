package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;

public interface BlogPostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogPost record);

    int insertSelective(BlogPost record);

    BlogPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogPost record);

    int updateByPrimaryKey(BlogPost record);
}
