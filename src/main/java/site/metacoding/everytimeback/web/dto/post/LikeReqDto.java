package site.metacoding.everytimeback.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeReqDto {

    private Integer likeCount;
    private Integer postId;

}
