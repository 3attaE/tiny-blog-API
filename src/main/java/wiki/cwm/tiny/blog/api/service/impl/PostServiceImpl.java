package wiki.cwm.tiny.blog.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import wiki.cwm.tiny.blog.api.common.Constants;
import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.common.StarTypeEnum;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogComment;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.BlogCommentMapper;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.BlogPostMapper;
import wiki.cwm.tiny.blog.api.dao.redis.RedisDao;
import wiki.cwm.tiny.blog.api.service.IPostService;
import wiki.cwm.tiny.blog.api.service.bo.CommentBO;
import wiki.cwm.tiny.blog.api.service.bo.ListBO;
import wiki.cwm.tiny.blog.api.service.bo.PostBO;
import wiki.cwm.tiny.blog.api.service.bo.StarBO;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {

    private final BlogPostMapper postMapper;
    private final BlogCommentMapper commentMapper;
    private final RedisDao redisDao;

    @PostConstruct
    public void init() {
        log.info("PostServiceImpl init star list");
        List<BlogPost> blogPosts = postMapper.iteratorAll(null, 10);
        while (blogPosts.size() > 0) {
            blogPosts.forEach(post -> redisDao.set(Constants.STAR_PREFIX + post.getId(), String.valueOf(post.getStarNum())));
            Long id = blogPosts.get(blogPosts.size() - 1).getId();
            blogPosts = postMapper.iteratorAll(id, 10);
        }

    }

    public PostServiceImpl(BlogPostMapper postMapper, BlogCommentMapper commentMapper, RedisDao redisDao) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.redisDao = redisDao;
    }

    @Override
    public Long create(PostBO postBO) {
        BlogPost blogPost = postBO.toDAO();
        postMapper.insertSelective(blogPost);
        return blogPost.getId();
    }

    @Override
    public Long update(PostBO postBO) {
        BlogPost blogPost = postBO.toDAO();
        return postMapper.updateByPrimaryKeySelective(blogPost);
    }

    @Override
    public ListBO list(Integer offset, Integer limit) {
        Long count = postMapper.count();
        if (count == 0) {
            return new ListBO(Collections.emptyList(), 0L);
        }

        List<BlogPost> blogPosts = postMapper.selectByLimit(offset, limit);
        List<PostBO> postBOS = blogPosts.stream().map(PostBO::new).collect(Collectors.toList());
        // 更新点赞缓存
        List<Long> ids = blogPosts.stream().map(BlogPost::getId).collect(Collectors.toList());
        blogPosts.forEach(
                blogPost -> {
                    String star = redisDao.get(Constants.STAR_PREFIX + blogPost.getId());
                    if (StringUtils.isEmpty(star)) {
                        redisDao.set(Constants.STAR_PREFIX + blogPost.getId(), String.valueOf(blogPost.getStarNum()));
                    }
                }
        );
        return new ListBO(postBOS, count);
    }

    @Override
    public List<PostBO> search(String keywords) {
        List<BlogPost> blogPosts = postMapper.selectByKeywords(keywords);
        return blogPosts.stream().map(PostBO::new).collect(Collectors.toList());
    }

    @Override
    public Long comment(CommentBO commentBO) {
        BlogComment blogComment = commentBO.toDAO();
        commentMapper.insertSelective(blogComment);
        return blogComment.getId();
    }

    @Override
    public void star(StarBO starBO) throws ServiceException {
        if (starBO.getStarType().equals(StarTypeEnum.INCR)) {
            postMapper.incrStarNum(starBO.getPostId());
        } else if (starBO.getStarType().equals(StarTypeEnum.DECR)) {
            postMapper.decrStarNum(starBO.getPostId());
        } else {
            throw new ServiceException("不支持的操作类型");
        }

        redisDao.del(Constants.STAR_PREFIX + starBO.getPostId());
    }
}
