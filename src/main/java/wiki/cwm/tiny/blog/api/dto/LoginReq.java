package wiki.cwm.tiny.blog.api.dto;

import lombok.Data;

@Data
public class LoginReq {

    private String username;
    private String password;
}
