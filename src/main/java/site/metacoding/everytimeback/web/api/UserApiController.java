package site.metacoding.everytimeback.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.UserService;
import site.metacoding.everytimeback.web.dto.ResponseDto;
import site.metacoding.everytimeback.web.dto.user.EmailUpdateDto;
import site.metacoding.everytimeback.web.dto.user.LoginDto;
import site.metacoding.everytimeback.web.dto.user.PasswordUpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/api/user/username-same-check")
    public ResponseEntity<?> usernameSameCheck(String username) {
        boolean isNotSame = userService.유저네임중복검사(username);
        return new ResponseEntity<>(isNotSame, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult,
            HttpServletResponse response) {
        // 인증
        User userEntity = userService.로그인(loginDto);

        if (userEntity == null) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("principal", userEntity); // 세션에 저장

        // System.out.println(loginDto.getRemember()); // on

        // 쿠키에 저장
        if (loginDto.getRemember().equals("on")) {
            response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + ";path=/");
        }

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PutMapping("/s/api/user/{id}/password") // 주소 변경 필요?
    public ResponseDto<?> updatePassword(@PathVariable Integer id,
            @Valid @RequestBody PasswordUpdateDto passwordUpdateDto, BindingResult bindingResult) {
        userService.비밀번호수정(id, passwordUpdateDto);
        return new ResponseDto<>(1, "성공", null);
    }

    @PutMapping("/s/api/user/{id}/email") // 주소 변경 필요?
    public ResponseDto<?> updateEmail(@PathVariable Integer id, @Valid @RequestBody EmailUpdateDto emailUpdateDto,
            BindingResult bindingResult) {
        userService.이메일수정(id, emailUpdateDto);
        return new ResponseDto<>(1, "성공", null);
    }

}