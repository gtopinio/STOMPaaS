package github.gtopinio.STOMPaaS.models.helpers;

import github.gtopinio.STOMPaaS.models.DTOs.SocketDTO;
import github.gtopinio.STOMPaaS.models.interfaces.Validator;
import org.springframework.stereotype.Service;

@Service
public class SocketInputValidator implements Validator {

    @Override
    public boolean validate(SocketDTO input) {

        if (input.getMessageContent() == null || input.getMessageContent().isEmpty()) {
            return false;
        }

        if (input.getMessageType() == null) {
            return false;
        }

        if (input.getSenderUsername() == null || input.getSenderUsername().isEmpty()) {
            return false;
        }

        return true;
    }
}
