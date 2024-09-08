package github.gtopinio.STOMPaaS.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * SocketController
 * This handles the WebSocket connection and messaging.
 * TODO: Implement the following API endpoints:
 * - registerChatMember
 * - unregisterChatMember (handles session disconnect event)
 * - sendChatMessage (should use switch based on DTO - handles multi or single recipient)
 * - pingChatTyping (should use switch based on DTO)
 * - other APIS to follow
 */

@Controller
@CrossOrigin
@Slf4j
public class SocketController {
}
