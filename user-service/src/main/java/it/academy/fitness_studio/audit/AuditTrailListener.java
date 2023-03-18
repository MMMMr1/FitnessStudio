//package it.academy.fitness_studio.audit;
//
//import it.academy.fitness_studio.core.dto.user.UserDTO;
//import it.academy.fitness_studio.core.dto.user.UserModel;
//import it.academy.fitness_studio.entity.UserEntity;
//import org.json.JSONObject;
//
//import javax.persistence.PrePersist;
//import javax.persistence.PreRemove;
//import javax.persistence.PreUpdate;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.time.Instant;
//
//
//public class AuditTrailListener {
//    @PrePersist
//    public void prePersist(UserEntity userEntity) {
//        String action = "Создана запись в журнале пользователей";
//        UserModel createdBy = (UserModel)userEntity.createdBy;
//        sendAudit(userEntity,createdBy, action);
//    }
//
//    @PreUpdate
//    public void preUpdate(UserEntity userEntity) {
//        String action = "Обновлена запись в журнале пользователей";
//
//        UserModel lastModifiedBy = (UserModel)userEntity.lastModifiedBy;
//        sendAudit(userEntity,lastModifiedBy,action);
//    }
//
//    @PreRemove
//    public void preRemove(UserEntity userEntity) {
//        String action = "Удалена запись в журнале пользователей";
//        UserModel lastModifiedBy = (UserModel)userEntity.lastModifiedBy;
//        sendAudit(userEntity,lastModifiedBy, action);
//    }
//    private void sendAudit(UserEntity userEntity,UserModel auditor, String action){
//        JSONObject object =new JSONObject();
//        object.put("uuid", auditor.getUuid());
//        object.put("mail", auditor.getMail());
//        object.put("fio", auditor.getName());
//        object.put("role", auditor.getRole());
//        object.put("text", action);
//        object.put("type","USER");
//        object.put("id",userEntity.getUuid());
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://audit-service:8080/api/v1/audit"))
//                .setHeader("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();
////            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//    }
//
//    private String uuid;
//    private Instant dtCreate ;
//    private UserDTO user;
//    private String text;
//    //    Создана запись в журнале питания
////    Описание действия пользователя
//    private String USER;
//    private String id;
//}
