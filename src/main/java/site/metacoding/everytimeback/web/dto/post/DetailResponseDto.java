package site.metacoding.everytimeback.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailResponseDto {
    private Post post;
    private boolean auth;
}
