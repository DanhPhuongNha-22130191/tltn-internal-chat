package com.tltn.identity.application.port;

public interface EmailSender {

    void sendVerificationEmail(String toEmail, String token);

    void sendPasswordResetEmail(String toEmail, String token);
}
