package site.metacoding.everytimeback.domain.message;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "fromUserId")
    @ManyToOne
    private User formUser;

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser;

    @JoinColumn(name = "postId")
    @ManyToOne
    private Post post;

    @Lob // 4GB
    @Column(nullable = true)
    private String msg;

    @CreatedDate
    private LocalDateTime createDate;
}
