package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogComment;

public class DummyCommentMapper implements BlogCommentMapper{
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public Long insert(BlogComment record) {
        return null;
    }

    @Override
    public Long insertSelective(BlogComment record) {
        return record.getId();
    }

    @Override
    public BlogComment selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Long updateByPrimaryKeySelective(BlogComment record) {
        return null;
    }

    @Override
    public Long updateByPrimaryKey(BlogComment record) {
        return null;
    }
}
