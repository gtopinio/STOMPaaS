package github.gtopinio.STOMPaaS.models.classes;

import github.gtopinio.STOMPaaS.models.enums.MessageType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketMessage {
    private String content;
    private String senderUsername;
    private UUID senderSocketId;
    private UUID socketRoomId;
    private MessageType type;
}
