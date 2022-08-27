package wiki.cwm.tiny.blog.api.common;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private Integer code;

    public ServiceException() {
        super();
        this.code = ExceptionEnum.INTERNAL_SERVER_ERROR.getCode();
    }

    public ServiceException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public ServiceException(String message) {
        super(message);
        this.code = ExceptionEnum.INTERNAL_SERVER_ERROR.getCode();
    }

}
