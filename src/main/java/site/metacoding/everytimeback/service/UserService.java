package site.metacoding.everytimeback.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.domain.user.UserRepository;
import site.metacoding.everytimeback.web.dto.user.EmailUpdateDto;
import site.metacoding.everytimeback.web.dto.user.LoginDto;
import site.metacoding.everytimeback.web.dto.user.PasswordUpdateDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

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

}
