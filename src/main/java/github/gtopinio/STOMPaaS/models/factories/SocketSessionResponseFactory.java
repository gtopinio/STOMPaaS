package github.gtopinio.STOMPaaS.models.factories;

import github.gtopinio.STOMPaaS.models.helpers.SocketSessionResponse;
import org.springframework.http.HttpStatus;

import java.util.UUID;


public class SocketSessionResponseFactory {

    public static SocketSessionResponse createSuccessResponse(UUID socketRoomId, String message) {
        return SocketSessionResponse.of(socketRoomId, message, HttpStatus.OK);
    }

    public static SocketSessionResponse createErrorResponse(UUID socketRoomId, String message) {
        return SocketSessionResponse.of(socketRoomId, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static SocketSessionResponse createNotFoundResponse(UUID socketRoomId, String message) {
        return SocketSessionResponse.of(socketRoomId, message, HttpStatus.NOT_FOUND);
    }

    public static SocketSessionResponse createBadRequestResponse(UUID socketRoomId, String message) {
        return SocketSessionResponse.of(socketRoomId, message, HttpStatus.BAD_REQUEST);
    }

    public static SocketSessionResponse createUnauthorizedResponse(UUID socketRoomId, String message) {
        return SocketSessionResponse.of(socketRoomId, message, HttpStatus.UNAUTHORIZED);
    }

    public static SocketSessionResponse createForbiddenResponse(UUID socketRoomId, String message) {
        return SocketSessionResponse.of(socketRoomId, message, HttpStatus.FORBIDDEN);
    }

    public static SocketSessionResponse createConflictResponse(UUID socketRoomId, String message) {
        return SocketSessionResponse.of(socketRoomId, message, HttpStatus.CONFLICT);
    }
}
