package wiki.cwm.tiny.blog.api.common;

import org.springframework.util.DigestUtils;

public class Md5Utils {

    public static String encode(String s) {
        return DigestUtils.md5DigestAsHex(s.getBytes()).toUpperCase();
    }

}
