package site.metacoding.everytimeback.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.UserService;
import site.metacoding.everytimeback.util.UtilValid;
import site.metacoding.everytimeback.web.dto.user.FindUsernameDto;
import site.metacoding.everytimeback.web.dto.user.JoinDto;
import site.metacoding.everytimeback.web.dto.user.PasswordResetReqDto;

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

    @GetMapping("/s/user/{id}/password")
    public String passwordUpdateForm(@PathVariable Integer id) {
        return "/user/passwordUpdateForm";
    }

    @GetMapping("/s/user/{id}/email")
    public String emailUpdateForm(@PathVariable Integer id) {
        return "/user/emailUpdateForm";
    }

    @GetMapping("/user/find-username-form")
    public String findUsernameForm() {
        return "/user/findUsernameForm";
    }

    @GetMapping("/user/find-username")
    public String findUsername(@Valid FindUsernameDto findUsernameDto, BindingResult bindingResult) {

        UtilValid.요청에러처리(bindingResult);

        userService.유저네임보내주기(findUsernameDto);

        return "redirect:/user/loginForm";
    }

    @GetMapping("/user/password-reset-form")
    public String passwordResetForm() {
        return "/user/passwordResetForm";
    }

    @PostMapping("/user/password-reset")
    public String passwordReset(@Valid PasswordResetReqDto passwordResetReqDto, BindingResult bindingResult) {

        UtilValid.요청에러처리(bindingResult);

        userService.패스워드초기화(passwordResetReqDto);

        return "redirect:/user/loginForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinDto joinDto, BindingResult bindingResult) {

        // 핵심로직
        userService.회원가입(joinDto.toEntity());

        return "redirect:/user/loginForm";
    }
}