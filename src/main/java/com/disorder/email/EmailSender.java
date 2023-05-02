package com.disorder.email;

public interface EmailSender {
    void sendEmail(String receiver, String mailContent);
}
