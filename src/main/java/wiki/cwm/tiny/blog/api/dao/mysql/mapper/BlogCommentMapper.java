package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogComment;

public interface BlogCommentMapper {
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
    int insert(BlogComment record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(BlogComment record);

    /**
     * 根据主键查找记录
     * @param id
     * @return
     */
    BlogComment selectByPrimaryKey(Long id);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BlogComment record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(BlogComment record);
}
