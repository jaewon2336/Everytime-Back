package site.metacoding.everytimeback.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.post.PostRepository;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void 글쓰기(Post post) {
        postRepository.save(post);
    }

    public Page<Post> 글목록보기(String keyword, Pageable pageable) {
        return postRepository.findByTitleContaining(keyword, pageable);
    }

    public Post 글상세보기(Integer id) {
        Optional<Post> postOp = postRepository.findById(id);
        if (postOp.isPresent()) {
            Post postEntity = postOp.get();
            return postEntity;
        } else {
            throw new RuntimeException("존재하지 않는 게시물입니다.");
        }
    }
}
