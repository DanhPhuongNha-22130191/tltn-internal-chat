package com.tltn.identity.infrastructure.smtp;

import com.tltn.identity.application.port.EmailSender;
import org.springframework.stereotype.Component;

@Component
public class MockEmailSender implements EmailSender {

    @Override
    public void sendVerificationEmail(String toEmail, String token) {
        System.out.println("Sending Verification Email to: " + toEmail);
        System.out.println("Link: http://rescuehub.com/verify?token=" + token);
    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String token) {
        System.out.println("Sending Password Reset Email to: " + toEmail);
        System.out.println("Link: http://rescuehub.com/reset?token=" + token);
    }
}
