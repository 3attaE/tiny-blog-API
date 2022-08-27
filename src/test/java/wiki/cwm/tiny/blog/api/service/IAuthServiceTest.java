package wiki.cwm.tiny.blog.api.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import wiki.cwm.tiny.blog.api.common.ServiceException;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class IAuthServiceTest {

    private static DummyAuthService iAuthService ;

    @BeforeAll
    public static void init() {
        iAuthService = new DummyAuthService();
        iAuthService.setSecret("secret");
    }

    @Test
    public void test_generate_token_and_verify_success() {
        Long before = 1L;
        String generate = iAuthService.generate(String.valueOf(before));
        Long userId = iAuthService.verify(generate);
        assertThat(userId).isEqualTo(before);
    }

    @Test
    public void test_verify_token_fail() throws InterruptedException {
        Long before = 1L;
        String generate = iAuthService.openGenerate(String.valueOf(before), TimeUnit.SECONDS.toMillis(1));
        Thread.sleep(2 * 1000);
        assertThatExceptionOfType(ServiceException.class).isThrownBy(() -> iAuthService.verify(generate));
    }

}