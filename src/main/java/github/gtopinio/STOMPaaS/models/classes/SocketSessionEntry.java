package github.gtopinio.STOMPaaS.models.classes;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketSessionEntry {
    private UUID senderSocketId;
    private UUID organizationId;
}
