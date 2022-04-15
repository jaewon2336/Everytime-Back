package site.metacoding.everytimeback.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.PostService;
import site.metacoding.everytimeback.web.dto.post.WriteDto;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;
    private final HttpSession session;

    @PostMapping("/s/post")
    public ResponseEntity<?> write(@RequestBody WriteDto writeDto) { // ResponseEntity는 @RequestBody가 없어도 json으로 파싱해준다
                                                                     // 아닌데?

        User principal = (User) session.getAttribute("principal");
        Post post = writeDto.toEntity(principal);

        postService.글쓰기(post);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

}
