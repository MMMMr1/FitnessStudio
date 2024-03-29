package it.academy.fitness_studio.audit;

import it.academy.fitness_studio.core.dto.UserDetailsDTO;
import it.academy.fitness_studio.web.utils.UserHolder;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Aspect
@Component
public class AuditAspect {
    @Value("${audit.url}")
    private String auditUrl;
    @AfterReturning(
            pointcut = "@annotation(auditable)",
            argNames = "auditable, uuid", returning = "uuid")
    public void doAudit(Auditable auditable, UUID uuid) {
        AuditCode value = auditable.auditCode();
        AuditEntityType type = auditable.auditType();
        UserHolder userHolder = new UserHolder();
        UserDetailsDTO user = userHolder.getUser();
        sendAudit(user, uuid, value.getDescription(), type.toString());
    }
    private void sendAudit(UserDetailsDTO auditor, UUID uuid, String action, String type) {
        JSONObject object = new JSONObject();
        object.put("uuid", auditor.getUuid());
        object.put("mail", auditor.getMail());
        object.put("fio", auditor.getName());
        object.put("role", auditor.getRole());
        object.put("text", action);
        object.put("type", type);
        object.put("id", uuid);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(auditUrl))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
