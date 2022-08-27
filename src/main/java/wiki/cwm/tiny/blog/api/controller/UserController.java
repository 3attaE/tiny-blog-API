package wiki.cwm.tiny.blog.api.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import wiki.cwm.tiny.blog.api.common.ExceptionEnum;
import wiki.cwm.tiny.blog.api.dto.LoginReq;
import wiki.cwm.tiny.blog.api.dto.Result;
import wiki.cwm.tiny.blog.api.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequestMapping(path = "/api/users/")
@RestController()
@Slf4j
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public Result<Void> login(@RequestBody LoginReq req, HttpServletResponse response) {
        if (Objects.isNull(req) || StringUtils.isEmpty(req.getUsername()) ||StringUtils.isEmpty(req.getPassword())) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }
        log.info("username {} user login", req.getUsername());

        try {
            String token = userService.login(req.getUsername(), req.getPassword());
            response.addCookie(new Cookie("token", token));
            return Result.success(null);
        }catch (Exception e) {
            log.error("login error", e);
            return Result.fail(ExceptionEnum.LOGIN_TOKEN_ERROR);
        }
    }

    @GetMapping("logout")
    public Result<Void> logout(HttpServletRequest requestContext) {
        if (Objects.isNull(requestContext.getHeader("Authorization"))) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }
        try {
            userService.logout(requestContext.getHeader("Authorization"));
            return Result.success(null);
        }catch (Exception e) {
            log.error("logout error", e);
            return Result.failFromException(e);
        }
    }

}
