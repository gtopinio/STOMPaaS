package github.gtopinio.STOMPaaS.models.helpers;

import github.gtopinio.STOMPaaS.models.classes.SocketSessionEntry;
import github.gtopinio.STOMPaaS.models.classes.SocketUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class SocketSessionMapper {
    /**
     * This map is used to store the socket session mapping.
     * The key is the UUID of the socket room, which says that the chat is active if it exists.
     */
    private final Map<UUID, SocketSessionEntry> socketSessionMapping;

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

    /**
     * This method is used to create a new socket session entry.
     *
     * @param categories The list of categories.
     * @param isMultipleUsers The boolean value indicating if the session is for multiple users.
     */
    public SocketSessionEntry createSocketSessionEntry(
            List<String> categories,
            Boolean isMultipleUsers
    ) {
        return SocketSessionEntry.builder()
                .socketUserList(new CopyOnWriteArrayList<>())
                .socketRoomCategoryList(categories)
                .isForMultipleUsers(isMultipleUsers)
                .build();
    }

    /**
     * This method is used for socket sessions that are trying to JOIN a room.
     * If the return value is true, the user is successfully added to the room.
     * If the return value is false, the user is already in the room.
     *
     * @param senderSocketId The UUID of the sender socket.
     * @param organizationId The UUID of the organization.
     * @param categories The list of categories.
     * @param socketRoomId The UUID of the socket room.
     * @param isMultipleUsers The boolean value indicating if the session is for multiple users.
     */
    public UUID upsertSocketSession(
            UUID senderSocketId,
            UUID organizationId,
            List<String> categories,
            UUID socketRoomId,
            Boolean isMultipleUsers
    ) {
        // TODO: Logic with organization ID (involves DB)
        // TODO: For random-room chats (non-persistent), use category list

        // Check if UUID is active
        if (this.doesSocketRoomExist(socketRoomId)) {
            // Existing chat

            // Get the socket session entry
            SocketSessionEntry socketSessionEntry = this.socketSessionMapping.get(socketRoomId);
            List<SocketUser> socketUserList = socketSessionEntry.getSocketUserList();

            // Check if the user is already in the room
            for (SocketUser socketUser : socketUserList) {
                if (socketUser.getSenderSocketId().equals(senderSocketId)) {
                    // User is already in the room
                    return null;
                }
            }

            if (
                (socketSessionEntry.getIsForMultipleUsers() && !isMultipleUsers) ||
                (!socketSessionEntry.getIsForMultipleUsers() && isMultipleUsers)
            ) {
                // Room is for multiple users, but user is trying to join as a single user
                // Or room is for single user, but user is trying to join as multiple users
                return null;
            }

            // Add the user to the room
            socketUserList.add(this.createSocketUser(senderSocketId, organizationId));
            socketSessionEntry.setSocketUserList(socketUserList);
            this.socketSessionMapping.put(socketRoomId, socketSessionEntry);
            log.info("Socket room updated: {}", socketRoomId);
            log.info("Updated Socket room mapping: {}", this.socketSessionMapping);
            return socketRoomId;
        } else {
            // New chat / Create a new socket session entry
            SocketSessionEntry socketSessionEntry = this.createSocketSessionEntry(categories, isMultipleUsers);
            socketSessionEntry.getSocketUserList().add(this.createSocketUser(senderSocketId, organizationId));
            this.socketSessionMapping.put(socketRoomId, socketSessionEntry);
            log.info("Socket room created: {}", socketRoomId);
            log.info("Current Socket room mapping: {}", this.socketSessionMapping);
            return socketRoomId;
        }
    }

    public boolean removeSocketSession(
        UUID senderSocketId,
        UUID socketRoomId
    ) {
        if (this.doesSocketRoomExist(socketRoomId)) {
            SocketSessionEntry socketSessionEntry = this.socketSessionMapping.get(socketRoomId);
            List<SocketUser> socketUserList = socketSessionEntry.getSocketUserList();

            for (SocketUser socketUser : socketUserList) {
                if (socketUser.getSenderSocketId().equals(senderSocketId)) {
                    socketUserList.remove(socketUser);
                    socketSessionEntry.setSocketUserList(socketUserList);
                    this.socketSessionMapping.put(socketRoomId, socketSessionEntry);
                    return true;
                }
            }

            this.cleanUpSocketRoom(socketRoomId);
        }
        return false;
    }

    private void cleanUpSocketRoom(UUID socketRoomId) {
        if (this.doesSocketRoomExist(socketRoomId)) {
            SocketSessionEntry socketSessionEntry = this.socketSessionMapping.get(socketRoomId);
            if (socketSessionEntry.getSocketUserList().isEmpty()) {
                this.socketSessionMapping.remove(socketRoomId);
            }
        }
    }
}
