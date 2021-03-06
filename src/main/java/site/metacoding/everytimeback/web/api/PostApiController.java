package site.metacoding.everytimeback.web.api;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.PostService;
import site.metacoding.everytimeback.web.dto.post.DetailResponseDto;
import site.metacoding.everytimeback.web.dto.post.WriteDto;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;
    private final HttpSession session;

    // 글쓰기
    @PostMapping("/s/post")
    public ResponseEntity<?> write(@RequestBody WriteDto writeDto) {
        User principal = (User) session.getAttribute("principal");
        Post post = writeDto.toEntity(principal);
        postService.글쓰기(post);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // 글목록
    @GetMapping("/api/post/list")
    public ResponseEntity<?> list(String keyword, Integer page,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> posts = postService.글목록보기(keyword, pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // 글상세보기
    @GetMapping("/api/post/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        Post postEntity = postService.글상세보기(id);
        User principal = (User) session.getAttribute("principal");
        boolean auth = false;

        // 권한체크
        if (principal != null) {
            if (principal.getId() == postEntity.getUser().getId()) {
                auth = true;
            }

            if (postEntity.isAnonyCheck() == true) {
                postEntity.getUser().setUsername("익명");
            }
        }

        System.out.println("익명인가요?" + postEntity.getUser().getUsername());
        DetailResponseDto detailResponseDto = new DetailResponseDto(postEntity, auth);
        return new ResponseEntity<>(detailResponseDto, HttpStatus.OK);
    }

    // 글삭제하기
    @DeleteMapping("/s/api/post/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        postService.글삭제하기(id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // 글수정하기
    @PutMapping("/s/post/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Post post) {

        // 인증 -> 인터셉터가 처리
        // 권한
        User principal = (User) session.getAttribute("principal");
        Post postEntity = postService.글상세보기(id);
        if (postEntity.getUser().getId() != principal.getId()) {
            throw new RuntimeException("해당 게시글을 수정할 권한이 없습니다.");
        }

        postService.글수정하기(post, id);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // 공감카운팅
    @PutMapping("/s/post/{postId}/like")
    public ResponseEntity<?> likeUp(@PathVariable Integer postId) {
        // postId받아서 -> findById하고 -> likeCount +1해서 -> update
        Post postEntity = postService.좋아요카운팅(postService.글상세보기(postId));
        return new ResponseEntity<>(postEntity, HttpStatus.OK);
    }
}