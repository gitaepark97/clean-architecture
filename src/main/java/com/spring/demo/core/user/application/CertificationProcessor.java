package com.spring.demo.core.user.application;

import com.spring.demo.core.user.application.port.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CertificationProcessor {

    private final MailSender mailSender;

    void send(String email, Long userId, String certificationCode) {
        String title = "Please certify your email address";
        String certificationUrl = generateCertificationUrl(userId, certificationCode);
        String content = String.format("<p>Please click the following link to certify your email address:</p><a href=\"%s\">verify</a>", certificationUrl);

        mailSender.send(email, title, content);
    }

    String generateCertificationUrl(long userId, String certificationCode) {
        return String.format("http://localhost:8080/api/auth/verify?userId=%d&certificationCode=%s", userId, certificationCode);
    }

}
