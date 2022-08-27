package wiki.cwm.tiny.blog.api.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.DummyBlogUserMapper;
import wiki.cwm.tiny.blog.api.dao.redis.DummyRedisDao;
import wiki.cwm.tiny.blog.api.service.DummyAuthService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class UserServiceImplTest {
    private static UserServiceImpl userService;
    private static DummyAuthService authService;
    private static DummyRedisDao redisDao;

    @BeforeEach
    public  void init() {
        authService = new DummyAuthService();
        redisDao = new DummyRedisDao();
        userService = new UserServiceImpl(authService, new DummyBlogUserMapper(), redisDao);
    }

    @Test
    public void test_login_success() {
        String login = userService.login("test", "test");
        Long userId = authService.verify(login);
        assertThat(userId).isEqualTo(1L);
    }

    @Test
    @Disabled
    public void test_verify_success() {
        String token = userService.login("test", "test");
        redisDao.set("test", token);
        Long verify = userService.verify(token);
        assertThat(verify).isEqualTo(1L);
    }

//    @Test
    public void test_verify_error() {
        String token = userService.login("test", "test");
        redisDao.set("test", "test");
        assertThatExceptionOfType(ServiceException.class).isThrownBy(() -> userService.verify(token));
    }

    @Test
    public void test_logout_success() {
        String token = userService.login("test", "test");
        userService.logout(token);
        assertThatExceptionOfType(ServiceException.class).isThrownBy(() -> userService.verify(token));
    }

}