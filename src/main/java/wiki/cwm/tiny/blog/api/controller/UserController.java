package wiki.cwm.tiny.blog.api.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wiki.cwm.tiny.blog.api.common.GsonUtils;
import wiki.cwm.tiny.blog.api.dto.LoginReq;
import wiki.cwm.tiny.blog.api.dto.LoginResp;
import wiki.cwm.tiny.blog.api.dto.Result;
import wiki.cwm.tiny.blog.api.service.IUserService;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(path = "/api/users/")
@RestController()
@Slf4j
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

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
