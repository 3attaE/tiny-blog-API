package wiki.cwm.tiny.blog.api.service;

import wiki.cwm.tiny.blog.api.service.bo.UserBO;

public interface IUserService {

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    UserBO login(String userName, String password);

}
