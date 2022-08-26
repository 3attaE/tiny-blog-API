package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogUser;

public interface BlogUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    BlogUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);
}
