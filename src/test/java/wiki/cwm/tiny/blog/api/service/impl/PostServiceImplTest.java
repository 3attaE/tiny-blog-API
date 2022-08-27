package wiki.cwm.tiny.blog.api.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import wiki.cwm.tiny.blog.api.common.StarTypeEnum;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.DummyCommentMapper;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.DummyPostMapper;
import wiki.cwm.tiny.blog.api.dao.redis.DummyRedisDao;
import wiki.cwm.tiny.blog.api.service.bo.CommentBO;
import wiki.cwm.tiny.blog.api.service.bo.ListBO;
import wiki.cwm.tiny.blog.api.service.bo.PostBO;
import wiki.cwm.tiny.blog.api.service.bo.StarBO;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


class PostServiceImplTest {

    private static PostServiceImpl postService;
    private static DummyRedisDao redisDao;
    private static DummyPostMapper postMapper;
    private static DummyCommentMapper commentMapper;

    @BeforeAll
    public static void init() {
        redisDao = new DummyRedisDao();
        postMapper = new DummyPostMapper();
        commentMapper = new DummyCommentMapper();

        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("test");
        blogPost.setContent("test");
        blogPost.setStarNum(0);
        blogPost.setCreateId(1L);
        blogPost.setUpdateId(1L);
        blogPost.setCreateTime(new Date());
        blogPost.setUpdateTime(new Date());
        postMapper.insert(blogPost);

        postService = new PostServiceImpl(postMapper,commentMapper, redisDao);
    }

    @Test
    public void test_create_success() {
        PostBO post = new PostBO();
        post.setId(1L);
        Long aLong = postService.create(post);
        assertThat(aLong).isEqualTo(1L);
    }

    @Test
    public void test_update_success() {
        PostBO post = new PostBO();
        post.setId(1L);
        Long aLong = postService.update(post);
        assertThat(aLong).isEqualTo(1L);
    }

    @Test
    public void test_list() {
        ListBO list = postService.list(0, 1);
        assertThat(list.getTotal()).isEqualTo(1);
        assertThat(list.getList().size()).isEqualTo(1);
    }

    @Test
    public void test_comment_success() {
        CommentBO bo = new CommentBO();
        bo.setPostId(1L);
        Long comment = postService.comment(bo);
        assertThat(comment).isEqualTo(1L);
    }

    @Test
    public void test_incr_star_success() {
        StarBO bo = new StarBO();
        bo.setStarType(StarTypeEnum.INCR);
        postService.star(bo);
        BlogPost after = postMapper.selectByPrimaryKey(null);
        assertThat(after.getStarNum()).isEqualTo(1L);
    }

    @Test
    public void test_decr_star_success() {
        StarBO bo = new StarBO();
        bo.setStarType(StarTypeEnum.DECR);
        postService.star(bo);
        BlogPost after = postMapper.selectByPrimaryKey(null);
        assertThat(after.getStarNum()).isEqualTo(0L);
    }


}