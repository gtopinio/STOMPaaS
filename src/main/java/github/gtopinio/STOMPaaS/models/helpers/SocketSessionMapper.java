package github.gtopinio.STOMPaaS.models.helpers;

import github.gtopinio.STOMPaaS.models.classes.SocketSessionEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketSessionMapper {
    private final Map<UUID, List<SocketSessionEntry>> socketSessionMapping; // Key: SocketRoomId, Value: List of entries in a room

    public SocketSessionMapper() {
        this.socketSessionMapping = new ConcurrentHashMap<>();
    }
}
