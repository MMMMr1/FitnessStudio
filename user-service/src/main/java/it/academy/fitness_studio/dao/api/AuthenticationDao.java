package it.academy.fitness_studio.dao.api;


import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthenticationDao extends CrudRepository<UserEntity,String> {
    Optional<UserEntity> findByMail(String mail);
}
