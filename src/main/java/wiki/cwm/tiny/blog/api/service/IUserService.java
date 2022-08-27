package wiki.cwm.tiny.blog.api.service;

import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.service.bo.UserBO;

public interface IUserService {

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password) throws ServiceException;

    /**
     * 验证token
     * @param token
     * @return
     * @throws ServiceException
     */
    Long verify(String token) throws ServiceException;

    /**
     * 退出
     * @param token
     */
    void logout(String token);
}
