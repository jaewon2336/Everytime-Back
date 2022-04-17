package site.metacoding.everytimeback.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/post/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("postId", id);
        return "post/detail";
    }

}
