package it.academy.fitness_studio.audit;

import it.academy.fitness_studio.core.dto.audit.AuditModel;
import it.academy.fitness_studio.core.dto.user.UserDetailsDTO;
import it.academy.fitness_studio.web.utils.UserHolder;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Aspect
@Component
public class AuditAspect {

    public static final String HTTP_AUDIT_SERVICE_8080_API_V_1_AUDIT = "http://audit-service:8080/api/v1/audit/report";


    @AfterReturning( "@annotation(audited)")
    public void audit(Audited audited) {
        AuditCode code =  audited.auditCode();
        AuditEntityType  type =  audited.auditType();
        UserHolder userHolder = new UserHolder();
        UserDetailsDTO user = userHolder.getUser();
        sendAudit(user, " ", code.getDescription(), type.toString());
    }
    @AfterReturning(
            pointcut="@annotation(auditable)",
            argNames = "auditable, auditmodel", returning = "auditmodel")
    public void doAudit (Auditable auditable, AuditModel auditModel) {
        AuditCode value = auditable.auditCode();
        AuditEntityType type = auditable.auditType();
        UserHolder userHolder = new UserHolder();
        UserDetailsDTO user = userHolder.getUser();
        sendAudit(user, auditModel.getUuid().toString(), value.getDescription(), type.toString());
    }

        private void sendAudit(UserDetailsDTO auditor, String uuid, String action, String type ){
        JSONObject object =new JSONObject();
        object.put("uuid", auditor.getUuid());
        object.put("mail", auditor.getMail());
        object.put("fio", auditor.getName());
        object.put("role", auditor.getRole());
        object.put("text", action);
        object.put("type",type);
        object.put("id",uuid);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HTTP_AUDIT_SERVICE_8080_API_V_1_AUDIT))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        }
}
