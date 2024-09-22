package github.gtopinio.STOMPaaS.models.helpers;

import github.gtopinio.STOMPaaS.models.DTOs.SocketDTO;
import github.gtopinio.STOMPaaS.models.enums.MessageType;
import github.gtopinio.STOMPaaS.models.interfaces.Validator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SocketInputValidator implements Validator {

    @Override
    public boolean validate(SocketDTO input) {

        if (input.getMessageContent() == null || input.getMessageContent().isEmpty()) {
            return false;
        }

        if (input.getMessageType() == null || !isEnumValue(input.getMessageType().name(), MessageType.class)) {
            return false;
        }

        if (input.getSenderUsername() == null || input.getSenderUsername().isEmpty()) {
            return false;
        }

        if (input.getSenderSocketId() == null || !isUUID(input.getSenderSocketId().toString())) {
            return false;
        }

        if (input.getReceiverSocketId() != null && !isUUID(input.getReceiverSocketId().toString())) {
            return false;
        }

        if (input.getOrganizationId() != null && !isUUID(input.getOrganizationId().toString())) {
            return false;
        }

        return true;
    }

    @Override
    public <T extends Enum<T>> boolean isEnumValue(String value, Class<T> enumClass) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean isUUID(String value) {
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
