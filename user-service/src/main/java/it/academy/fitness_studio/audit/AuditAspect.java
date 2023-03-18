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
            argNames = "auditable, uuid", returning = "uuid")
    public void doAudit (Auditable auditable, UUID uuid) {
        AuditCode value = auditable.value();
        UserModel user = new UserHolder().getUser();
        sendAudit(user, uuid, value.name());
    }
//    @AfterReturning(pointcut = "@annotation(it.academy.fitness_studio.audit.Audit)", returning = "uuid")
//    private void log(JoinPoint joinPoint, UUID uuid) throws IllegalAccessException {
////        String methodMessage = getMethodMessage(joinPoint);
//        UserHolder userHolder = new UserHolder();
//        UserModel user = userHolder.getUser();
//        sendAudit(user, uuid, "Dddd");

//        Class<?> objectClass = object.getClass();
//        Map<String, String> logElements = new HashMap<>();
//        Set<String> displayFields = new HashSet<>();
//        for (Field field : objectClass.getDeclaredFields()) {
//            field.setAccessible(true);
//            if (field.isAnnotationPresent(Logger.class)) {
//                if (checkIsShowDataEnabled(field)) {
//                    logElements.put(getFieldValue(field), String.valueOf(field.get(object)));
//                } else {
//                    displayFields.add(getFieldValue(field));
//                }
//            }
//        }
//        logger.info("method message: " + methodMessage);
//        logger.info("displayed fields: " + String.join(", ", displayFields));
//        logger.info("displayed fields with data: " + logElements.toString());


//    private static String getMethodMessage(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//          method.getAnnotation( );
//        return aspectLogger.value();
//    }

//    private static String getFieldValue(Field field) {
//        String fieldValue = field.getAnnotation(Logger.class).value();
//        return fieldValue.isEmpty() ? field.getName() : fieldValue;
//    }
//
//    private static boolean checkIsShowDataEnabled(Field field) {
////        return field.getAnnotation(Logger.class).showData();
//    }
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
