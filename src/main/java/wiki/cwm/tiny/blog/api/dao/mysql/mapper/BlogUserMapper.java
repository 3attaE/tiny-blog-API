package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import org.apache.ibatis.annotations.Param;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogUser;

public interface BlogUserMapper {
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
    int insert(BlogUser record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(BlogUser record);

    /**
     * 根据主键查找记录
     * @param id
     * @return
     */
    BlogUser selectByPrimaryKey(Long id);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BlogUser record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(BlogUser record);

    /**
     * 根据用户名和密码查询用户
     */
    BlogUser selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
