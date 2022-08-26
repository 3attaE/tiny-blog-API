package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;

public interface BlogPostMapper {
    /**
     * 根据主键删除记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入新记录
     * @param record
     * @return
     */
    int insert(BlogPost record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(BlogPost record);

    /**
     * 根据主键查找记录
     * @param id
     * @return
     */
    BlogPost selectByPrimaryKey(Long id);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BlogPost record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(BlogPost record);

}
