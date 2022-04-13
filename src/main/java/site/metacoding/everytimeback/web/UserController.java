package site.metacoding.everytimeback.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.service.UserService;
import site.metacoding.everytimeback.web.dto.user.JoinDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(JoinDto joinDto) {
        return "/user/joinForm";
    }

    @PostMapping("/join")
    public String join(JoinDto joinDto) {

        // 핵심로직
        userService.회원가입(joinDto.toEntity());

        return "redirect:/loginForm";
    }
}