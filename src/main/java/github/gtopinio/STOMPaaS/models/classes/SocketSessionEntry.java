package github.gtopinio.STOMPaaS.models.classes;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketSessionEntry {
    private List<SocketUser> socketUserList;
    private List<String> socketRoomCategoryList; // Can be used for categorizing rooms (i.e., like a tag); Can be null for persistent sessions
    private Boolean isForMultipleUsers;
}