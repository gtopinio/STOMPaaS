package github.gtopinio.STOMPaaS.models.DTOs;

import github.gtopinio.STOMPaaS.models.enums.MessageType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketDTO {
    private String messageContent;
    private String senderUsername;
    private String receiverUsername; // Can only be used if messageType is JOIN and session is 1-to-1
    private UUID senderSocketId; // Implementation is DB specific (can be omitted in non-DB implementations)
    private UUID receiverSocketId; // Implementation is DB specific (can be omitted in non-DB implementations)
    private UUID socketRoomId; // Can be null at first, but must be set before sending a message in existing session
    private UUID organizationId; // If org is not present, it is not DB specific
    private MessageType messageType;
}
