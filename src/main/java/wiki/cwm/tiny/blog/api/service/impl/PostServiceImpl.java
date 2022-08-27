package wiki.cwm.tiny.blog.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.common.StarTypeEnum;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogPost;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.BlogCommentMapper;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.BlogPostMapper;
import wiki.cwm.tiny.blog.api.dao.redis.RedisDao;
import wiki.cwm.tiny.blog.api.service.IPostService;
import wiki.cwm.tiny.blog.api.service.bo.CommentBO;
import wiki.cwm.tiny.blog.api.service.bo.ListBO;
import wiki.cwm.tiny.blog.api.service.bo.PostBO;
import wiki.cwm.tiny.blog.api.service.bo.StartBO;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {

    private final BlogPostMapper postMapper;
    private final BlogCommentMapper commentMapper;
    private final RedisDao<Integer> redisDao;
    private final static String STAR_PREFIX = "post::star::";

    @PostConstruct
    public void init() {
        log.info("PostServiceImpl init star list");
        postMapper.selectAll().forEach(post -> redisDao.set(STAR_PREFIX + post.getId(), post.getStarNum()));

    }

    public PostServiceImpl(BlogPostMapper postMapper, BlogCommentMapper commentMapper, RedisDao<Integer> redisDao) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.redisDao = redisDao;
    }

    @Override
    public Long create(PostBO postBO) {
        return postMapper.insertSelective(postBO.toDAO());
    }

    @Override
    public Long update(PostBO postBO) {
        return postMapper.updateByPrimaryKeySelective(postBO.toDAO());
    }

    @Override
    public ListBO list(Integer offset, Integer limit) {
        Long count = postMapper.count();
        if (count == 0) {
            return new ListBO(Collections.emptyList(), 0L);
        }

        List<BlogPost> blogPosts = postMapper.selectByLimit(offset, limit);
        List<PostBO> postBOS = blogPosts.stream().map(PostBO::new).collect(Collectors.toList());
        return new ListBO(postBOS, count);
    }

    @Override
    public List<PostBO> search(String keywords) {
        List<BlogPost> blogPosts = postMapper.selectByKeywords(keywords);
        return blogPosts.stream().map(PostBO::new).collect(Collectors.toList());
    }

    @Override
    public Long comment(CommentBO commentBO) {
        return commentMapper.insert(commentBO.toDAO());
    }

    @Override
    public void star(StartBO startBO) throws ServiceException {
        if (startBO.getStarType().equals(StarTypeEnum.INCR)) {
            redisDao.incr(STAR_PREFIX + startBO.getPostId());
        } else if (startBO.getStarType().equals(StarTypeEnum.DECR)) {
            redisDao.decr(STAR_PREFIX + startBO.getPostId());
        } else {
            throw new ServiceException("不支持的操作类型");
        }
    }
}
