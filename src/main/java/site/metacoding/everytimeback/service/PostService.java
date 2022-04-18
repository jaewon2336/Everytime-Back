package site.metacoding.everytimeback.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.post.PostRepository;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
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

    @Transactional
    public void 글삭제하기(Integer id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(Post post, Integer id) {
        // 영속화
        Optional<Post> postOp = postRepository.findById(id);

        // 변경감지
        if (postOp.isPresent()) {
            Post postEntity = postOp.get();
            postEntity.setTitle(post.getTitle());
            postEntity.setContent(post.getContent());
        }
    } // 더티체킹 완료 (수정됨)

    @Transactional
    public Post 좋아요카운팅(Post post) {
        // 영속화
        Optional<Post> postOp = postRepository.findById(post.getId());

        // 변경감지
        if (postOp.isPresent()) {
            Post postEntity = postOp.get();
            postEntity.setLikeCount(post.getLikeCount() + 1);
            return postEntity;
        } else {
            throw new RuntimeException("좋아요에 실패했습니다.");
        }
    } // 더티체킹 완료 (수정됨)
}
