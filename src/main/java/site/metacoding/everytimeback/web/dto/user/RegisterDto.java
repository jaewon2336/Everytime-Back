package site.metacoding.everytimeback.web.dto.user;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.everytimeback.domain.certificate.Certificate;
import site.metacoding.everytimeback.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    private Integer certificateNo;

    private MultipartFile certificate;

    public Certificate toEntity(String imgUrl, User principal) {
        Certificate certificate = new Certificate();

        certificate.setCertificateNo(certificateNo);
        certificate.setImgUrl(imgUrl);
        certificate.setUser(principal);

        return certificate;
    }
}
