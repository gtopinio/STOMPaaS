package github.gtopinio.STOMPaaS.models.DTOs;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {
    private String receiverEmail; // Note: The sender email is fixed in this current implementation
    private String subject;
    private String message;
}
