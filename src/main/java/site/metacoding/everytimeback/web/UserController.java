package site.metacoding.everytimeback.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.UserService;
import site.metacoding.everytimeback.web.dto.user.JoinDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/user/joinForm")
    public String joinForm(JoinDto joinDto) {
        return "/user/joinForm";
    }

    @GetMapping("/s/user/{id}")
    public String userInfo(@PathVariable Integer id, Model model) {
        User userEntity = userService.회원정보(id);
        model.addAttribute("user", userEntity);
        return "/user/updateForm";
    }

    @PostMapping("/join")
    public String join(JoinDto joinDto) {

        // 핵심로직
        userService.회원가입(joinDto.toEntity());

        return "redirect:/loginForm";
    }
}