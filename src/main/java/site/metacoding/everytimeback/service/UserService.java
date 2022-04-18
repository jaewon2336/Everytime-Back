package site.metacoding.everytimeback.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.domain.user.UserRepository;
import site.metacoding.everytimeback.handler.ex.CustomException;
import site.metacoding.everytimeback.util.email.EmailUtil;
import site.metacoding.everytimeback.web.dto.user.EmailUpdateDto;
import site.metacoding.everytimeback.web.dto.user.FindUsernameDto;
import site.metacoding.everytimeback.web.dto.user.LoginDto;
import site.metacoding.everytimeback.web.dto.user.PasswordResetReqDto;
import site.metacoding.everytimeback.web.dto.user.PasswordUpdateDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailUtil emailUtil;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    public boolean 유저네임중복검사(String username) {
        Optional<User> userOp = userRepository.findByUsername(username);

        if (userOp.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    public User 로그인(LoginDto loginDto) {
        return userRepository.mLogin(loginDto.getUsername(), loginDto.getPassword());
    }

    public User 회원정보(Integer id) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            return userOp.get();
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니당"); // id가 없을 경우 수정 필요
        }
    }

    @Transactional
    public void 비밀번호수정(Integer id, PasswordUpdateDto passwordUpdateDto) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.setPassword(passwordUpdateDto.getPassword());
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다");
        }
    }

    @Transactional
    public void 이메일수정(Integer id, EmailUpdateDto emailUpdateDto) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.setEmail(emailUpdateDto.getEmail());
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다");
        }
    }

    public void 유저네임보내주기(FindUsernameDto findUsernameDto) {
        String receiverEmail = "";
        String receiverUsername = "";

        Optional<User> userOp = userRepository.findByEmail(
                findUsernameDto.getEmail());

        if (userOp.isPresent()) {

            User userEntity = userOp.get();
            receiverEmail = userEntity.getEmail();
            receiverUsername = userEntity.getUsername();

        } else {
            throw new CustomException("해당 이메일이 존재하지 않습니다");
        }

        // System.out.println("받는 사람 이메일 : " + receiverEmail);
        // System.out.println("받는 사람 유저네임 : " + receiverUsername);
        emailUtil.sendEmail("\"" + receiverEmail + "\"", "요청하신 이메일의 ID", "ID : " + receiverUsername);

    }

    @Transactional
    public void 패스워드초기화(PasswordResetReqDto passwordResetReqDto) {
        String receiverEmail = "";
        String randomPassword = "";

        Optional<User> userOp = userRepository.findByUsernameAndEmail(
                passwordResetReqDto.getUsername(),
                passwordResetReqDto.getEmail());

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            receiverEmail = userEntity.getEmail();

            // 6자의 난수 생성 후 비밀번호 지정
            Integer randomNum = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
            randomPassword = randomNum.toString();

            userEntity.setPassword(randomPassword);
            System.out.println(userEntity.getPassword());

        } else {
            throw new CustomException("해당 이메일이 존재하지 않습니다");
        }
        emailUtil.sendEmail("\"" + receiverEmail + "\"", "비밀번호가 초기화 되었습니다",
                "초기화된 비밀번호는 " + randomPassword + " 입니다. 로그인 후 비밀번호를 재설정하십시오.");
    }
}
