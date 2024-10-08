package github.gtopinio.STOMPaaS.models.DTOs;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {
    private String senderEmail; // Note: The receiver email is fixed in this current implementation
    private String subject;
    private String message;
    private String senderFirstName;
    private String senderLastName;
}
