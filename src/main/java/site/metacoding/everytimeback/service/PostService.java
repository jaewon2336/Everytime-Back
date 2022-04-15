package site.metacoding.everytimeback.service;

import org.springframework.stereotype.Service;

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
}
