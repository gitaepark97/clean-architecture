package com.spring.demo.core.user.infrastructure;

import com.spring.demo.core.user.application.port.MailSender;
import com.spring.demo.global.exception.ApplicationException;
import com.spring.demo.global.exception.ErrorCode;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
class MailSenderImpl implements MailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(String email, String title, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject(title);
            message.setText(content, "utf-8", "html");
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
