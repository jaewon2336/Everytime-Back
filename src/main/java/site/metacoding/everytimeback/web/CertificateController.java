package site.metacoding.everytimeback.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.CertificateService;
import site.metacoding.everytimeback.web.dto.user.RegisterDto;

@RequiredArgsConstructor
@Controller
public class CertificateController {

    private final CertificateService certificateService;
    private final HttpSession session;

    @GetMapping("/certificate/register-form")
    public String reigsterForm() {
        return "/certificate/registerForm";
    }

    @PostMapping("/certificate/register")
    public String register(RegisterDto registerDto) {

        User principal = (User) session.getAttribute("principal");

        certificateService.증명서제출하기(registerDto, principal);
        return "certificate/registerForm"; // qnaForm으로 돌아가도록 수정할 것!!!
    }
}
