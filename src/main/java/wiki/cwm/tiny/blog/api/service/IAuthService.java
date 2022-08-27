package wiki.cwm.tiny.blog.api.service;

import wiki.cwm.tiny.blog.api.common.ServiceException;

public interface IAuthService {

    /**
     * 校验 token
     * @param s
     */
    Long verify(String s) throws ServiceException;

    /**
     *
     * @param username
     * @param password
     * @return
     */
    String generate(String userId) throws ServiceException;
}
