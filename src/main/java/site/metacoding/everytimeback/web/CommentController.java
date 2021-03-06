package site.metacoding.everytimeback.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.comment.Comment;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.service.CommentService;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;
    private final HttpSession session;

    @PostMapping("/s/post/{postId}/comment")
    public String write(@PathVariable Integer postId, Comment comment) {

        User principal = (User) session.getAttribute("principal");

        comment.setUser(principal);

        commentService.댓글쓰기(comment, postId);
        return "redirect:/post/" + postId;
    }
}
