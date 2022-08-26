package wiki.cwm.tiny.blog.api.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    PARAM_INVALID_EXCEPTION(400, "参数不合法"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    LOGIN_ERROR(510,"用户名或密码错误"),
    POST_ERROR(520,"发布或更新错误"),
    STAR_ERROR(530,"点赞失败"),
    COMMENT_ERRO(540,"评论失败");

    private Integer code;
    private String message;
}
