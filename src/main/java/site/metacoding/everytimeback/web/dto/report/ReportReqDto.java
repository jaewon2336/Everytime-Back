package site.metacoding.everytimeback.web.dto.report;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.post.Post;
import site.metacoding.everytimeback.domain.postReport.PostReport;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportReqDto {

    @NotBlank
    @Size(max = 60)
    private String reason;

    public PostReport toEntity(Post postEntity) {
        PostReport postReport = new PostReport();
        postReport.setReason(reason);
        postReport.setPost(postEntity);

        return postReport;
    }

}
