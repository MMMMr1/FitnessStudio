package it.academy.fitness_studio.core.converter.converter;

import it.academy.fitness_studio.core.dto.audit.AuditDTO;
import it.academy.fitness_studio.core.enums.AuditType;
import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditDTOToAuditEntity implements Converter<AuditDTO, AuditEntity> {
    @Override
    public AuditEntity convert(AuditDTO user) {
        return new AuditEntity(
                user.getUuid(),
                user.getMail(),
                user.getFio(),
                Enum.valueOf(UserRole.class, user.getRole()),
                user.getText(),
                Enum.valueOf(AuditType.class, user.getType()),
                user.getId());
    }
}
