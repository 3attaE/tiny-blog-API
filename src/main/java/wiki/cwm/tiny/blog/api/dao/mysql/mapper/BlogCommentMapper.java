package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogComment;

public interface BlogCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);
}
