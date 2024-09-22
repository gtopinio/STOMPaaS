package github.gtopinio.STOMPaaS.models.helpers;

import github.gtopinio.STOMPaaS.models.classes.SocketSessionEntry;
import github.gtopinio.STOMPaaS.models.classes.SocketUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketSessionMapper {
    private final Map<UUID, SocketSessionEntry> socketSessionMapping; // Key: socketRoomId = can be persistent or non-persistent (for random chats)

    public SocketSessionMapper() {
        this.socketSessionMapping = new ConcurrentHashMap<>();
    }

    /**
     * This method is used to check if a socket room exists.
     *
     * @param socketRoomId The UUID of the socket room.
     */
    public boolean doesSocketRoomExist(UUID socketRoomId) {
        return this.socketSessionMapping.containsKey(socketRoomId);
    }

    /**
     * This method is used to create a new socket session entry.
     *
     * @param senderSocketId The UUID of the sender socket.
     * @param organizationId The UUID of the organization.
     */
    public SocketUser createSocketUser(UUID senderSocketId, UUID organizationId) {
        return SocketUser.builder()
                .senderSocketId(senderSocketId)
                .organizationId(organizationId)
                .build();
    }

    public void upsertSocketSession(
            UUID senderSocketId,
            UUID organizationId,
            List<String> categories,
            UUID socketRoomId
    ) {
        // Two cases:
        // 1. For generic chat rooms (private or grouped).
        //      - User is trying to join a (private/public) session room and is messaging (even without other users)
        //      - User already has a session room and is messaging
        //
        // 2. For anonymous chat rooms (private or grouped).
        //      - User is trying to join and only has some categories (can be empty) and has isMulti variable
        //      - User already has a session room id and is messaging
        if (this.doesSocketRoomExist(socketRoomId)) {
            // This means that the socket room already exists and a new user is trying to join
        } else {

        }
    }

}
