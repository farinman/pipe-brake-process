package farinman.ba.pipe_brake_process.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Die SendMailHelper Klasse wird verwendet um E-Mails zu versenden.
 */
public class SendMailHelper {
    
    @Autowired
    public JavaMailSender emailSender;
    
    /**
     * Simple Nachricht senden
     *
     * @param to the to
     * @param subject the subject
     * @param text the text
     * @param from the from
     */
    public void sendSimpleMessage(String to, String subject, String text, String from) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(from);
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }
}
