package com.spring.demo.core.user.application.port;

public interface MailSender {

    void send(String email, String title, String content);

}
