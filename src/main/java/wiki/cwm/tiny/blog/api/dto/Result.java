package wiki.cwm.tiny.blog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wiki.cwm.tiny.blog.api.common.ExceptionEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "success", data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> fail(ExceptionEnum exceptionEnum) {
        return new Result<>(exceptionEnum.getCode(),exceptionEnum.getMessage(),null);
    }

    public static <T> Result<T> fail(ExceptionEnum exceptionEnum, String message) {
        return new Result<>(exceptionEnum.getCode(), message,null);
    }

}
