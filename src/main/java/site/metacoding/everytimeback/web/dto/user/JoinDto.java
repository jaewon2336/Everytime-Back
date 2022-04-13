package site.metacoding.everytimeback.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data // Getter(필수), Setter, toString
public class JoinDto {

    private String name;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String school;
    private String studentNo;

    public User toEntity() {
        User user = new User();

        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setSchool(school);
        user.setStudentNo(studentNo);

        return user;
    }
}
