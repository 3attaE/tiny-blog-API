package wiki.cwm.tiny.blog.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.cwm.tiny.blog.api.dto.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(path = "/api/posts/")
@RestController()
@Slf4j
public class PostController {

    @PostMapping("create")
    public Result<Long> creat(HttpServletRequest requestContext, @RequestBody PostDTO req) {
        return null;
    }

    @PostMapping("update")
    public Result<Long> update(HttpServletRequest requestContext, @RequestBody PostDTO req) {
        return null;
    }

    @PostMapping("list")
    public Result<ListResp> update(@RequestBody ListReq req) {
        return null;
    }

    @PostMapping("comment")
    public Result<Long> comment(@RequestBody CommentDTO req) {
        return null;
    }

    @PostMapping("star")
    public Result<Void> star(@RequestBody StarDTO req) {
        return null;
    }

    @PostMapping("search")
    public Result<ListResp> search(@RequestBody SearchReq req) {
        return null;
    }
}
