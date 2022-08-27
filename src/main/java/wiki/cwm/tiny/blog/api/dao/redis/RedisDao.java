package wiki.cwm.tiny.blog.api.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisDao<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setWithExpire(String key, T value, int expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expire, timeUnit);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void incr(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void decr(String key) {
        redisTemplate.opsForValue().decrement(key);
    }
}
