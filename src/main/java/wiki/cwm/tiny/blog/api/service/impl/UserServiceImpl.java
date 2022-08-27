package wiki.cwm.tiny.blog.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import wiki.cwm.tiny.blog.api.common.Constants;
import wiki.cwm.tiny.blog.api.common.ExceptionEnum;
import wiki.cwm.tiny.blog.api.common.Md5Utils;
import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.dao.mysql.entity.BlogUser;
import wiki.cwm.tiny.blog.api.dao.mysql.mapper.BlogUserMapper;
import wiki.cwm.tiny.blog.api.dao.redis.RedisDao;
import wiki.cwm.tiny.blog.api.service.IAuthService;
import wiki.cwm.tiny.blog.api.service.IUserService;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {


    private final IAuthService authService;
    private final BlogUserMapper userMapper;
    private final RedisDao redisDao;

    public UserServiceImpl(IAuthService authService, BlogUserMapper userMapper, RedisDao redisDao) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.redisDao = redisDao;
    }

    @Override
    public String login(String username, String password) throws ServiceException {
        String encode = Md5Utils.encode(password);

        BlogUser blogUser = userMapper.selectByUsernameAndPassword(username, encode);
        if (Objects.isNull(blogUser)) {
            throw new ServiceException(ExceptionEnum.LOGIN_TOKEN_ERROR);
        }

        String token = authService.generate(blogUser.getId().toString());
        redisDao.setWithExpire(Constants.TOKEN_PREFIX + blogUser.getId(), token, 30, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public Long verify(String token) throws ServiceException {

        Long userId;

        try {
            userId = authService.verify(token);
        } catch (ServiceException e) {
            throw e;
        }

        String s = redisDao.get(Constants.TOKEN_PREFIX + userId);
        if (StringUtils.isEmpty(s)) {
            throw new ServiceException(ExceptionEnum.LOGIN_TOKEN_ERROR);
        }

        return userId;
    }

    @Override
    public void logout(String token) {
        Long userId = null;
        try {
            userId = authService.verify(token);
            redisDao.del(Constants.TOKEN_PREFIX + userId);
        } catch (ServiceException e) {
            log.error("delete token {} userId {}", token, userId, e);
        } catch (Exception e) {
            throw e;
        }
    }
}
