package wiki.cwm.tiny.blog.api.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import wiki.cwm.tiny.blog.api.common.GsonUtils;
import wiki.cwm.tiny.blog.api.dto.LoginReq;
import wiki.cwm.tiny.blog.api.dto.LoginResp;
import wiki.cwm.tiny.blog.api.dto.Result;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(path = "/api/users/")
@RestController()
@Slf4j
public class UserController {

    @PostMapping("login")
    public Result<LoginResp> login(@RequestBody LoginReq req) {

        return null;
    }

    @GetMapping("logout")
    public Result<Void> logout(HttpServletRequest requestContext) {
        log.info(GsonUtils.toJson(requestContext.getHeader("Authorization")));
        return Result.success(null);
    }

}
