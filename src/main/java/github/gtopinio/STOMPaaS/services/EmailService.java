package github.gtopinio.STOMPaaS.services;

import github.gtopinio.STOMPaaS.models.DTOs.EmailDTO;
import github.gtopinio.STOMPaaS.models.factories.ResponseFactory;
import github.gtopinio.STOMPaaS.models.helpers.EmailInputValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailInputValidator emailInputValidator;

    public EmailService(EmailInputValidator emailInputValidator) {
        this.emailInputValidator = emailInputValidator;
    }

    public ResponseEntity<String> sendEmail(EmailDTO emailDTO) {
        if (!emailInputValidator.validate(emailDTO)) {
            return ResponseFactory.createBadRequestResponse("Invalid email input");
        }

        // Some email sending logic here

        return ResponseFactory.createSuccessResponse("Email sent successfully");
    }
}
