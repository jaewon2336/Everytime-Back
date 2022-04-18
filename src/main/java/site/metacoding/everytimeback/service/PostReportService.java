package site.metacoding.everytimeback.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.postReport.PostReport;
import site.metacoding.everytimeback.domain.postReport.PostReportRepository;

@RequiredArgsConstructor
@Service
public class PostReportService {

    private final PostReportRepository postReportRepository;

    public void 신고하기(PostReport postReport) {
        postReportRepository.save(postReport);
    }

}
