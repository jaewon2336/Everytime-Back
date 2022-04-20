package site.metacoding.everytimeback.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.everytimeback.domain.certificate.Certificate;
import site.metacoding.everytimeback.domain.certificate.CertificateRepository;
import site.metacoding.everytimeback.domain.user.User;
import site.metacoding.everytimeback.util.UtilFileUpload;
import site.metacoding.everytimeback.web.dto.user.RegisterDto;

@RequiredArgsConstructor
@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 증명서제출하기(RegisterDto registerDto, User principal) {

        String imgUrl = UtilFileUpload.write(uploadFolder, registerDto.getCertificate());
        Certificate certificateUrl = registerDto.toEntity(imgUrl, principal);

        certificateRepository.save(certificateUrl);
    }

}
