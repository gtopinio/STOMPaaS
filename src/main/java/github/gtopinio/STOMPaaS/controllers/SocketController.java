package github.gtopinio.STOMPaaS.controllers;

import github.gtopinio.STOMPaaS.models.DTOs.SocketDTO;
import github.gtopinio.STOMPaaS.models.factories.SocketSessionResponseFactory;
import github.gtopinio.STOMPaaS.models.response.SocketSessionResponse;
import github.gtopinio.STOMPaaS.services.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * SocketController
 * This handles the WebSocket connection and messaging.
 * TODO: Implement the following API endpoints:
 * - linkSocketSession (done)
 * - unlinkSocketSession (handles session disconnect event) (done)
 * - sendChatMessage (should use switch based on DTO - handles multi or single recipient)
 * - pingChatTyping (should use switch based on DTO)
 * - other APIS to follow
 */

@Controller
@CrossOrigin
@Slf4j
public class SocketController {
    private final SocketService socketService;

    @Autowired
    public SocketController(
        SocketService socketService
    ) {
        this.socketService = socketService;
    }

    /**
     * This controller method is used to link the socket session to the desired socket room.
     *
     * @param input The SocketDTO object containing the socket connection details.
     * @param headerAccessor The SimpMessageHeaderAccessor object containing the message header.
     */
    @MessageMapping("/stomp.linkSocketSession")
    public SocketSessionResponse linkSocketSession(
        @Payload SocketDTO input,
        SimpMessageHeaderAccessor headerAccessor
    ) {
        try {
            return this.socketService.linkSocketSession(input, headerAccessor);
        } catch (Exception e) {
            log.error("Error linking socket session: {}", e.getMessage());
            return SocketSessionResponseFactory.createErrorResponse(null, e.getMessage());
        }
    }

    /**
     * This controller method is used to unlink the socket session from the desired socket room.
     *
     * @param event The SessionDisconnectEvent object containing the session disconnect event details.
     */
    @EventListener
    public SocketSessionResponse unlinkSocketSession(SessionDisconnectEvent event) {
        try {
            return this.socketService.unlinkSocketSession(event);
        } catch (Exception e) {
            log.error("Error unlinking socket session: {}", e.getMessage());
            return SocketSessionResponseFactory.createErrorResponse(null, e.getMessage());
        }
    }
}
