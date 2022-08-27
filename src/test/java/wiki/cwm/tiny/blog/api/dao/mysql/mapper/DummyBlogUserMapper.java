package wiki.cwm.tiny.blog.api.dao.mysql.mapper;

import wiki.cwm.tiny.blog.api.common.Md5Utils;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogUser;

public class DummyBlogUserMapper implements BlogUserMapper {

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(BlogUser record) {
        return 0;
    }

    @Override
    public int insertSelective(BlogUser record) {
        return 0;
    }

    @Override
    public BlogUser selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(BlogUser record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(BlogUser record) {
        return 0;
    }

    @Override
    public BlogUser selectByUsernameAndPassword(String username, String password) {
        BlogUser blogUser = new BlogUser();
        blogUser.setId(1L);
        blogUser.setUsername("test");
        blogUser.setPassword(Md5Utils.encode("test"));
        blogUser.setNickname("testUser");
        return blogUser;
    }
}