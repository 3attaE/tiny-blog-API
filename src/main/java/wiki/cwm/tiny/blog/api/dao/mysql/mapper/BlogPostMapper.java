package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import org.apache.ibatis.annotations.Param;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;

import java.util.List;

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
    Long insert(BlogPost record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    Long insertSelective(BlogPost record);

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
    Long updateByPrimaryKeySelective(BlogPost record);

    /**
     * 更新
     * @param record
     * @return
     */
    Long updateByPrimaryKey(BlogPost record);

    /**
     * 分页查询
     * @param offset
     * @param limit
     * @return
     */
    List<BlogPost> selectByLimit(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 总量
     * @return
     */
    Long count();

    /**
     * 全文查询
     * @param keywords
     * @return
     */
    List<BlogPost> selectByKeywords(@Param("keywords") String keywords);

    /**
     * 全量查询
     * @return
     */
    List<BlogPost> selectAll();
}
