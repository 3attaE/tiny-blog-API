package wiki.cwm.tiny.blog.api.dao.redis;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Disabled
class RedisDaoTest {

    @Autowired
    RedisDao redisDao;

    @Test
    public void test_redis() {
        redisDao.set("test:key", "1");
        assertThat(redisDao.get("test:key")).isEqualTo("1");
    }


}