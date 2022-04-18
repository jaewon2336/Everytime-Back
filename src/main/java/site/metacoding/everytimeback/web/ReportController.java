package site.metacoding.everytimeback.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.postReport.PostReport;
import site.metacoding.everytimeback.service.PostReportService;
import site.metacoding.everytimeback.service.PostService;
import site.metacoding.everytimeback.web.dto.report.ReportReqDto;

@RequiredArgsConstructor
@RestController
public class ReportController {

    private final PostReportService postRepostService;
    private final PostService postService;

    @PostMapping("/s/post/{postId}/report")
    public ResponseEntity<?> postReport(@PathVariable Integer postId, @RequestBody ReportReqDto reportReqDto) {
        // postId로 셀렉트해서 report에 담기
        PostReport postReportEntity = reportReqDto.toEntity(postService.글상세보기(postId));

        postRepostService.신고하기(postReportEntity);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

}
