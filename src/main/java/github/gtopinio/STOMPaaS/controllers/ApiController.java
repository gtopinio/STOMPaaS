package github.gtopinio.STOMPaaS.controllers;

import github.gtopinio.STOMPaaS.models.DTOs.EmailDTO;
import github.gtopinio.STOMPaaS.models.factories.ResponseFactory;
import github.gtopinio.STOMPaaS.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
@Tag(name = "STOMPaaS API Controller", description = "API Controller for STOMPaaS")
public class ApiController {
    private final EmailService emailService;

    public ApiController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "Send an email", description = "Sends an email using the provided EmailDTO. This is sent to the email configured in the application.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/send-email")
    public CompletableFuture<ResponseEntity<String>> sendEmail(@RequestBody EmailDTO emailDTO) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.emailService.sendEmail(emailDTO);
            } catch (Exception e) {
                return ResponseFactory.createErrorResponse("Error sending email");
            }
        });
    }
}