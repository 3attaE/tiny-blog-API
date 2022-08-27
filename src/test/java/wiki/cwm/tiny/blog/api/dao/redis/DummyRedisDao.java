package wiki.cwm.tiny.blog.api.dao.redis;

import java.util.concurrent.TimeUnit;

public class DummyRedisDao<T> extends RedisDao<T>{

    private T value;

    @Override
    public void set(String key, T value) {
        this.value = value;
    }

    @Override
    public T get(String key) {
        return value;
    }

    @Override
    public void setWithExpire(String key, T value, int expire, TimeUnit timeUnit) {
        this.value = value;
    }

    @Override
    public void del(String key) {
        this.value  = null;
    }
}
