package it.academy.fitness_studio.core.converter.converter;

import it.academy.fitness_studio.core.dto.user.AuditDTO;
import it.academy.fitness_studio.core.enums.TypeOfAudit;
import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditDTOToAuditEntity implements Converter<AuditDTO, AuditEntity> {
    @Override
    public AuditEntity convert(AuditDTO user) {
        String role = user.getRole();
        UserRole userRole = Enum.valueOf(UserRole.class, role);

        return new AuditEntity(
                user.getUuid(),
                user.getMail(),
                user.getFio(),
                userRole,
                user.getText(),
                Enum.valueOf(TypeOfAudit.class, user.getType()),
                user.getId());
    }
}
