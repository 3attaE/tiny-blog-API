package wiki.cwm.tiny.blog.api.common;

import com.google.gson.Gson;


public class GsonUtils {

    private static final Gson GSON = new Gson();

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }
}
