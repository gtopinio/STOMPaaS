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

import java.util.concurrent.CompletableFuture;

/**
 * SocketController
 * This handles the WebSocket connection and messaging.
 * It is responsible for linking and unlinking the socket session to the desired socket room.
 * It is also responsible for sending messages to the desired socket room.
 * It is also responsible for handling the socket session disconnect event.
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
    public CompletableFuture<SocketSessionResponse> linkSocketSession(
            @Payload SocketDTO input,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.socketService.linkSocketSession(input, headerAccessor);
            } catch (Exception e) {
                log.error("Error linking socket session: {}", e.getMessage());
                return SocketSessionResponseFactory.createErrorResponse(null, e.getMessage());
            }
        });
    }

    /**
     * This controller method is used to unlink the socket session from the desired socket room.
     *
     * @param event The SessionDisconnectEvent object containing the session disconnect event details.
     */
    @EventListener
    public CompletableFuture<SocketSessionResponse> unlinkSocketSession(SessionDisconnectEvent event) {
       return CompletableFuture.supplyAsync(() -> {
            try {
                return this.socketService.unlinkSocketSession(event);
            } catch (Exception e) {
                log.error("Error unlinking socket session: {}", e.getMessage());
                return SocketSessionResponseFactory.createErrorResponse(null, e.getMessage());
            }
        });
    }

    /**
     * This controller method is used to send a message to the desired socket room.
     *
     * @param input The SocketDTO object containing the socket message details.
     */
    @MessageMapping("/stomp.sendChatMessage")
    public CompletableFuture<SocketSessionResponse> sendSocketMessage(
            @Payload SocketDTO input
    ) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.socketService.sendSocketMessage(input);
            } catch (Exception e) {
                log.error("Error sending socket message: {}", e.getMessage());
                return SocketSessionResponseFactory.createErrorResponse(null, e.getMessage());
            }
        });
    }
}
