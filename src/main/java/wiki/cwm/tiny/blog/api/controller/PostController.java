package wiki.cwm.tiny.blog.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.cwm.tiny.blog.api.common.ExceptionEnum;
import wiki.cwm.tiny.blog.api.dto.*;
import wiki.cwm.tiny.blog.api.service.IPostService;
import wiki.cwm.tiny.blog.api.service.IUserService;
import wiki.cwm.tiny.blog.api.service.bo.CommentBO;
import wiki.cwm.tiny.blog.api.service.bo.ListBO;
import wiki.cwm.tiny.blog.api.service.bo.PostBO;
import wiki.cwm.tiny.blog.api.service.bo.StarBO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping(path = "/api/posts/")
@RestController()
@Slf4j
public class PostController {

    private final IPostService postService;
    private final IUserService userService;

    public PostController(IPostService postService, IUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("create")
    public Result<Long> creat(HttpServletRequest requestContext, @RequestBody PostDTO req) {
        if (Objects.isNull(requestContext.getHeader("Authorization"))) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }
        try {
            Long userId = userService.verify(requestContext.getHeader("Authorization"));
            req.setCreateId(userId);
            req.setUpdateId(userId);
            return Result.success(postService.create(new PostBO(req)));
        } catch (Exception e) {
            log.error("create post error", e);
            return Result.failFromException(e);
        }
    }

    @PostMapping("update")
    public Result<Long> update(HttpServletRequest requestContext, @RequestBody PostDTO req) {
        if (Objects.isNull(requestContext.getHeader("Authorization"))) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }
        try {
            Long userId = userService.verify(requestContext.getHeader("Authorization"));
            req.setUpdateId(userId);
            return Result.success(postService.update(new PostBO(req)));
        } catch (Exception e) {
            log.error("update post error", e);
            return Result.failFromException(e);
        }
    }

    @PostMapping("list")
    public Result<ListResp> list(@RequestBody ListReq req) {
        if (Objects.isNull(req) || Objects.isNull(req.getPage()) || Objects.isNull(req.getSize())) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }
        try {
            ListBO list = postService.list((req.getPage() - 1) * req.getSize(), req.getSize());
            List<PostDTO> postDTOS = list.getList().stream().map(PostBO::toDTO).collect(Collectors.toList());

            return Result.success(new ListResp(postDTOS, list.getTotal()));
        } catch (Exception e) {
            log.error("list error", e);
            return Result.failFromException(e);
        }
    }

    @PostMapping("comment")
    public Result<Long> comment(@RequestBody CommentDTO req) {
        if (Objects.isNull(req)) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }
        try {
            Long commentId = postService.comment(new CommentBO(req));
            return Result.success(commentId);
        } catch (Exception e) {
            log.error("comment error", e);
            return Result.failFromException(e);
        }

    }

    @PostMapping("star")
    public Result<Void> star(@RequestBody StarDTO req) {
        if (Objects.isNull(req)) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }

        try {
            postService.star(new StarBO(req));
            return Result.success(null);
        } catch (Exception e) {
            log.error("star error", e);
            return Result.failFromException(e);
        }

    }

    @PostMapping("search")
    public Result<ListResp> search(@RequestBody SearchReq req) {
        if (Objects.isNull(req)) {
            return Result.fail(ExceptionEnum.PARAM_INVALID_EXCEPTION);
        }

        try {
            List<PostBO> search = postService.search(req.getKeywords());
            List<PostDTO> postDTOS = search.stream().map(PostBO::toDTO).collect(Collectors.toList());
            return Result.success(new ListResp(postDTOS, (long) postDTOS.size()));
        } catch (Exception e) {
            log.error("search error", e);
            return Result.failFromException(e);
        }
    }
}
