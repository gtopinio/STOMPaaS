package github.gtopinio.STOMPaaS.models.helpers;

import github.gtopinio.STOMPaaS.models.DTOs.EmailDTO;
import github.gtopinio.STOMPaaS.models.interfaces.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class EmailInputValidator implements EmailValidator {
    @Override
    public boolean isEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    @Override
    public boolean validate(EmailDTO emailDTO) {
        if (emailDTO.getReceiverEmail() == null || emailDTO.getReceiverEmail().isEmpty()) {
            return false;
        }

        if (emailDTO.getSubject() == null || emailDTO.getSubject().isEmpty()) {
            return false;
        }

        if (emailDTO.getMessage() == null || emailDTO.getMessage().isEmpty()) {
            return false;
        }

        if (!isEmail(emailDTO.getReceiverEmail())) {
            return false;
        }

        return true;
    }
}
