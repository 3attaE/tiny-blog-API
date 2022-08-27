package wiki.cwm.tiny.blog.api.common;

public enum StarTypeEnum {

    /**
     * 1 点赞
     * 2 取消点赞
     */
    INCR,DECR;

    public static StarTypeEnum valueOfCode(Integer code) {
        switch (code) {
            case 1:
                return INCR;
            case 2:
                return DECR;
            default:
                return null;
        }
    }
}
