package it.academy.fitness_studio.core.converter.converter;
//
//import it.academy.fitness_studio.core.dto.user.UserModel;
//import it.academy.fitness_studio.core.enums.UserRole;
//import it.academy.fitness_studio.core.enums.UserStatus;
//import it.academy.fitness_studio.entity.AuditEntity;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.util.UUID;
//@Component
//public class UserEntityToUserModel implements Converter<AuditEntity, UserModel> {
//    @Override
//    public UserModel convert(AuditEntity userEntity) {
//        Instant dtCreate = userEntity.getDtCreate();
//        Instant dtUpdate = userEntity.getDtUpdate();
//        String fio = userEntity.getFio();
//        String mail = userEntity.getMail();
//        UserRole role = userEntity.getRole().getRole();
//        UserStatus status = userEntity.getStatus().getStatus();
//        UUID uuid = userEntity.getUuid();
//        return new UserModel(uuid, dtCreate, dtUpdate, mail, fio, role, status);
//    }
//}
