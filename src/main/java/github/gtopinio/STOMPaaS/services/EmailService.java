package github.gtopinio.STOMPaaS.services;

import github.gtopinio.STOMPaaS.models.DTOs.EmailDTO;
import github.gtopinio.STOMPaaS.models.factories.ResponseFactory;
import github.gtopinio.STOMPaaS.models.helpers.EmailInputValidator;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String myEmail;
    private final EmailInputValidator emailInputValidator;
    private final JavaMailSender javaMailSender;

    public EmailService(
        EmailInputValidator emailInputValidator,
        JavaMailSender javaMailSender
    ) {
        this.emailInputValidator = emailInputValidator;
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<String> sendEmail(EmailDTO emailDTO) {
        try {
            if (!emailInputValidator.validate(emailDTO)) {
                return ResponseFactory.createBadRequestResponse("Invalid email input");
            }

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(myEmail);
            helper.setTo(myEmail);
            helper.setSubject(emailDTO.getSubject());

            String htmlContent = this.prettifyEmailContent(emailDTO);

            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);

            log.info("Email sent successfully");
            return ResponseFactory.createSuccessResponse("Email sent successfully");
        } catch (Exception e) {
            log.error("Error sending email: {}", e.getMessage());
            return ResponseFactory.createErrorResponse("Error sending email");
        }
    }

    private String prettifyEmailContent(EmailDTO emailDTO) {
        return "<h1>STOMPaaS Email Notification</h1>"
                + "<p><b>From:</b> " + emailDTO.getSenderFirstName() + " " + emailDTO.getSenderLastName() + "</p>"
                + "<p><b>Message:</b></p>"
                + "<p>" + emailDTO.getMessage() + "</p>";
    }
}
