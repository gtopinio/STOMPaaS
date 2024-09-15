package github.gtopinio.STOMPaaS.services;

import github.gtopinio.STOMPaaS.models.helpers.SocketSessionResponse;
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

    public SocketService(
        SimpMessagingTemplate messagingTemplate
    ) {
        this.messagingTemplate = messagingTemplate;
        this.socketSessionMapping = new ConcurrentHashMap<>();
    }

    public SocketSessionResponse linkSocketSession() {
        // TODO: Implement this method
        return null;
    }
}
