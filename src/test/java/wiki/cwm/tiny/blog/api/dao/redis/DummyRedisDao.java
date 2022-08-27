package wiki.cwm.tiny.blog.api.dao.redis;

import java.util.concurrent.TimeUnit;

public class DummyRedisDao extends RedisDao{

    private String value;

    @Override
    public void set(String key, String value) {
        this.value = value;
    }

    @Override
    public String get(String key) {
        return value;
    }

    @Override
    public void setWithExpire(String key, String value, int expire, TimeUnit timeUnit) {
        this.value = value;
    }

    @Override
    public void del(String key) {
        this.value  = null;
    }
}
