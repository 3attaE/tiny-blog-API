package wiki.cwm.tiny.blog.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api")
@RestController()
public class TestController {

    @GetMapping()
    public String test() {
        return "test call first api";
    }

}