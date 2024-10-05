package github.gtopinio.STOMPaaS.models.classes;

import lombok.*;

import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketUser {
    private UUID senderSocketId;
    private UUID organizationId;
}
