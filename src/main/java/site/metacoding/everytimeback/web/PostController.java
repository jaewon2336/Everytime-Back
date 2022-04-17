package site.metacoding.everytimeback.web;

import javax.servlet.http.HttpSession;
import javax.websocket.PongMessage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.PostService;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final HttpSession session;

    // 메인
    @GetMapping("/")
    public String main() {
        return "/post/main";
    }

    // 글 상세보기
    @GetMapping("/post/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("postId", id);
        return "post/detail";
    }

    // 글 수정폼
    @GetMapping("/s/post/{id}/update")
    public String updateForm(@PathVariable Integer id, Model model) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            throw new RuntimeException("로그인이 필요한 서비스입니다.");
        }

        Post postEntity = postService.글상세보기(id);

        if (postEntity.getUser().getId() != principal.getId()) {
            throw new RuntimeException("수정할 권한이 없습니다.");
        }

        model.addAttribute("post", postEntity);

        return "post/updateForm";
    }

}
