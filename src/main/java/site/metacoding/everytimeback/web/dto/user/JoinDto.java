package site.metacoding.everytimeback.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data // Getter(필수), Setter, toString
public class JoinDto {

    @Size(min = 4, max = 15)
    @NotBlank
    private String name;

    @Size(min = 4, max = 20)
    @NotBlank
    private String username;

    @Size(min = 4, max = 20)
    @NotBlank
    private String password;

    @Size(min = 4, max = 30)
    @NotBlank
    private String nickname;

    @Size(min = 8, max = 60)
    @NotBlank
    @Email
    private String email;

    @Size(min = 4, max = 60)
    @NotBlank
    private String school;

    @Size(max = 30)
    @NotBlank
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
