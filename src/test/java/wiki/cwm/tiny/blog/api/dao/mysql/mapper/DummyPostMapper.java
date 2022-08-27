package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;

import java.util.Collections;
import java.util.List;

public class DummyPostMapper implements BlogPostMapper{

    private BlogPost blogPost;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public Long insert(BlogPost record) {
        blogPost = record;
        return blogPost.getId();
    }

    @Override
    public Long insertSelective(BlogPost record) {
        blogPost = record;
        return blogPost.getId();
    }

    @Override
    public BlogPost selectByPrimaryKey(Long id) {
        return blogPost;
    }

    @Override
    public Long updateByPrimaryKeySelective(BlogPost record) {
        blogPost = record;
        return blogPost.getId();
    }

    @Override
    public Long updateByPrimaryKey(BlogPost record) {
        return null;
    }

    @Override
    public List<BlogPost> selectByLimit(Integer offset, Integer limit) {
        return Collections.singletonList(blogPost);
    }

    @Override
    public Long count() {
        return 1L;
    }

    @Override
    public List<BlogPost> selectByKeywords(String keywords) {
        return null;
    }

    @Override
    public List<BlogPost> iteratorAll(Long cursor, Integer limit) {
        return null;
    }

    @Override
    public int incrStarNum(Long id) {
        blogPost.setStarNum(blogPost.getStarNum() + 1);
        return blogPost.getStarNum();
    }

    @Override
    public int decrStarNum(Long id) {
        if (blogPost.getStarNum() > 0) {
            blogPost.setStarNum(blogPost.getStarNum() - 1);
        }
        return blogPost.getStarNum();
    }
}
