package wiki.cwm.tiny.blog.api.service;

import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.service.impl.AuthServiceImpl;

public class DummyAuthService extends AuthServiceImpl {

    public DummyAuthService() {
        setSecret("secret");
    }

    public String openGenerate(String userId, Long time) throws ServiceException {
        return super.generateWithTime(userId, time);
    }
}
