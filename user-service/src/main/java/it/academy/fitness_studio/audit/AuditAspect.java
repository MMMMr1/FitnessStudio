package it.academy.fitness_studio.audit;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.service.UserHolder;
import org.apache.catalina.connector.Request;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Aspect
@Component
public class AuditAspect {
    private AuditAspect(){

    }
    @AfterReturning(
            pointcut="@annotation(auditable)",
            argNames = "auditable, usermodel", returning = "usermodel")
//    @AfterReturning(pointcut="com.xyz.myapp.SystemArchitecture.dataAccessOperation()",
//            returning="retVal")
    public void doAudit (Auditable auditable, UserModel userModel) {
        AuditCode value = auditable.value();
        UserHolder userHolder = new UserHolder();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserModel user =  principal instanceof String  ? userModel : userHolder.getUser();
        sendAudit(user, userModel.getUuid(), value.name());
    }

        private void sendAudit( UserModel auditor, UUID uuid, String action){
        JSONObject object =new JSONObject();
        object.put("uuid", auditor.getUuid());
        object.put("mail", auditor.getMail());
        object.put("fio", auditor.getName());
        object.put("role", auditor.getRole());
        object.put("text", action);
        object.put("type","USER");
        object.put("id",uuid);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://audit-service:8080/api/v1/audit"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();

                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        }
}
