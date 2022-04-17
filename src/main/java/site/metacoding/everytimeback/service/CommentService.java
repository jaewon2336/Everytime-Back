package site.metacoding.everytimeback.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.comment.Comment;
import site.metacoding.everytimeback.domain.comment.CommentRepository;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.post.PostRepository;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void 댓글쓰기(Comment comment, Integer postId) {
        Optional<Post> postOp = postRepository.findById(postId);

        if (postOp.isPresent()) {
            comment.setPost(postOp.get());
        } else {
            throw new RuntimeException("없는 게시글에 댓글을 작성할 수 없습니다.");
        }
        commentRepository.save(comment);
    }
}
