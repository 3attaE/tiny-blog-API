package wiki.cwm.tiny.blog.api.service;

import wiki.cwm.tiny.blog.api.common.ServiceException;

public interface IAuthService {

    /**
     * 校验 token
     */
    Long verify(String s) throws ServiceException;

    /**
     * 生成 token
     */
    String generate(String userId) throws ServiceException;
}
