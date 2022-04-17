package site.metacoding.everytimeback.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/api/post/list")
    public ResponseEntity<?> list(String keyword, Integer page,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        // System.out.println("pageable : " + pageable.getPageNumber());
        // System.out.println("page : " + page);

        Page<Post> posts = postService.글목록보기(keyword, pageable);

        // System.out.println("잘왔어? " + posts);
        // System.out.println("잘왔어? " + posts.getSize());

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        Post postEntity = postService.글상세보기(id);
        User principal = (User) session.getAttribute("principal");
        boolean auth = false;

        if (principal != null) {
            if (principal.getId() == postEntity.getUser().getId()) {
                auth = true;
            }
        }
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }

}
