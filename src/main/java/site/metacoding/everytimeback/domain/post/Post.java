package site.metacoding.everytimeback.domain.post;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.comment.Comment;
import site.metacoding.everytimeback.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String title;

    @Lob // 4GB
    @Column(nullable = false)
    private String content;

    @Column(length = 200, nullable = true)
    private String thumnail;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @JsonIgnoreProperties({ "post" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) // 연관관계의 주인의 변수명
    private List<Comment> comments;

    @Column(nullable = false)
    private Integer boardNo;

    private Integer likeCount;

    // @JsonIgnoreProperties({ "post" }) // messageConverter에게 알려주는 어노테이션
    // @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    // private Scrap scrap;

    private boolean anonyCheck;

    @Column(length = 200, nullable = true)
    private String hashTag;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;
}
