package github.gtopinio.STOMPaaS.services;

import github.gtopinio.STOMPaaS.models.DTOs.SocketDTO;
import github.gtopinio.STOMPaaS.models.factories.SocketSessionResponseFactory;
import github.gtopinio.STOMPaaS.models.helpers.SocketInputValidator;
import github.gtopinio.STOMPaaS.models.response.SocketSessionResponse;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketService {
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<UUID, List<UUID>> socketSessionMapping; // Key: SocketRoomId, Value: List of SocketIds of members in the room
    private final SocketInputValidator socketInputValidator;

    public SocketService(
        SimpMessagingTemplate messagingTemplate,
        SocketInputValidator socketInputValidator
    ) {
        this.messagingTemplate = messagingTemplate;
        this.socketSessionMapping = new ConcurrentHashMap<>();
        this.socketInputValidator = socketInputValidator;
    }

    /**
     * This service method is used to link the socket session to the desired socket room.
     *
     * @param input The SocketDTO object containing the socket connection details.
     * @param headerAccessor The SimpMessageHeaderAccessor object containing the message header.
     */
    public SocketSessionResponse linkSocketSession(
        @Payload SocketDTO input,
        SimpMessageHeaderAccessor headerAccessor
    ) {
        // Validate input
        boolean isValid = this.socketInputValidator.validate(input);
        if (!isValid) {
            return SocketSessionResponseFactory.createBadRequestResponse(null, "Invalid input");
        }

        // TODO: Implement this method
        return null;
    }
}
