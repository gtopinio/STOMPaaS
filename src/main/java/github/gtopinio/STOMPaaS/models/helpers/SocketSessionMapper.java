package github.gtopinio.STOMPaaS.models.helpers;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketSessionMapper {
    private final Map<UUID, List<UUID>> socketSessionMapping; // Key: SocketRoomId, Value: List of SocketIds of members in the room

    public SocketSessionMapper() {
        this.socketSessionMapping = new ConcurrentHashMap<>();
    }
}
