package site.metacoding.everytimeback.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.CommentService;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;
    private final HttpSession session;

    // data를 리턴하면 CommentApiController를 원래 만들어야한다.
    @DeleteMapping("/s/api/comment/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        // 권한처리
        // 세션의 id와 comment의 userId와 비교
        User principal = (User) session.getAttribute("principal");

        commentService.댓글삭제(id, principal);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

}
