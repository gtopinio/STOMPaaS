package github.gtopinio.STOMPaaS.models.DTOs;

import github.gtopinio.STOMPaaS.models.classes.SocketMessage;
import github.gtopinio.STOMPaaS.models.enums.MessageType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketDTO {
    private SocketMessage socketMessage;
    private String senderUsername; // Can be used for string templates
    private String receiverUsername; // Can be used for string templates
    private UUID senderSocketId; // Should not be null, must be created on the client side
    private UUID receiverSocketId; // Can be null if it's not a private persistent session; Should not be null at first if it's a trying to join in a private persistent session
    private UUID socketRoomId; // Can be null at first if a random room is trying to join (non-persistent); Should not be null at first if a persistent session is trying to join
    private UUID organizationId; // If org is not present, it is not DB specific. If it is, persistence is required
    private List<String> categories; // Can be used for categorizing rooms (i.e., like a tag); Can be null for persistent sessions
    private MessageType messageType;
    private Boolean isForMultipleUsers;
}
