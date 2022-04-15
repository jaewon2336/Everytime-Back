package site.metacoding.everytimeback.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.service.PostService;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String main() {
        return "/post/main";
    }

    @GetMapping("/post/list")
    public String board() {
        return "/post/list";
    }

}
