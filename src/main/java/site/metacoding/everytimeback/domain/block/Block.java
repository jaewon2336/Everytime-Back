package site.metacoding.everytimeback.domain.block;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "block_uk", columnNames = { "ownerUserId", "blockUserId" })
})
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "ownerUserId")
    @ManyToOne
    private User ownerUser;

    @JoinColumn(name = "blockUserId")
    @ManyToOne
    private User blockUser;

    @CreatedDate
    private LocalDateTime createDate;
}
