package github.gtopinio.STOMPaaS.models.interfaces;

import github.gtopinio.STOMPaaS.models.DTOs.EmailDTO;

public interface EmailValidator {
    boolean validate(EmailDTO emailDTO);
    boolean isEmail(String email);
}
