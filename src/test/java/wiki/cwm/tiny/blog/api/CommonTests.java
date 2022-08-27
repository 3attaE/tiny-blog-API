package wiki.cwm.tiny.blog.api;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import static org.assertj.core.api.Assertions.*;

public class CommonTests {

    @Test
    public void test_md5() {
        String before = "test";
        String after = DigestUtils.md5DigestAsHex(before.getBytes()).toUpperCase();
        assertThat(after).isEqualTo("098F6BCD4621D373CADE4E832627B4F6");
    }

}
