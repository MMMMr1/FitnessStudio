package it.academy.fitness_studio.core.converter.converter;

import it.academy.fitness_studio.core.dto.audit.AuditModel;
import it.academy.fitness_studio.core.dto.audit.ActorModel;
import it.academy.fitness_studio.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AuditEntityToAuditModel implements Converter< AuditEntity, AuditModel> {
    @Override
    public AuditModel convert(AuditEntity user) {


        return new AuditModel(
                user.getId(),
                user.getDtCreate(),
                new ActorModel( user.getUuid(),user.getMail(),user.getFio(),user.getRole()),

                user.getText(),
                user.getType(),
                user.getId_modified()
         );
    }
}
