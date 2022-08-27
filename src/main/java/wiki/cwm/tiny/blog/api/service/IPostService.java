package wiki.cwm.tiny.blog.api.service;

import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.service.bo.CommentBO;
import wiki.cwm.tiny.blog.api.service.bo.ListBO;
import wiki.cwm.tiny.blog.api.service.bo.PostBO;
import wiki.cwm.tiny.blog.api.service.bo.StartBO;

import java.util.List;

public interface IPostService {

    /**
     * 创建文章
     * @param postBO
     * @return
     */
    Long create(PostBO postBO);

    /**
     * 更新文章
     * @param postBO
     * @return
     */
    Long update(PostBO postBO);

    /**
     * 文章列表
     * @param offset
     * @param limit
     * @return
     */
    ListBO list(Integer offset, Integer limit);

    /**
     * 搜索
     * @param keywords
     * @return
     */
    List<PostBO> search(String keywords);

    /**
     * 评论
     * @param commentBO
     * @return
     */
    Long comment(CommentBO commentBO);

    /**
     * 点赞
     * @param startBO
     * @throws ServiceException
     */
    void star(StartBO startBO) throws ServiceException;

}
