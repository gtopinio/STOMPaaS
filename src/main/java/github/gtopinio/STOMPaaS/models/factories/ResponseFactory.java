package github.gtopinio.STOMPaaS.models.factories;

import org.springframework.http.ResponseEntity;

public class ResponseFactory {
    public static ResponseEntity<String> createSuccessResponse(String message) {
        return ResponseEntity.ok(message);
    }

    public static ResponseEntity<String> createErrorResponse(String message) {
        return ResponseEntity.status(500).body(message);
    }

    public static ResponseEntity<String> createNotFoundResponse(String message) {
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity<String> createBadRequestResponse(String message) {
        return ResponseEntity.badRequest().body(message);
    }

    public static ResponseEntity<String> createUnauthorizedResponse(String message) {
        return ResponseEntity.status(401).body(message);
    }

    public static ResponseEntity<String> createForbiddenResponse(String message) {
        return ResponseEntity.status(403).body(message);
    }

    public static ResponseEntity<String> createConflictResponse(String message) {
        return ResponseEntity.status(409).body(message);
    }
}
