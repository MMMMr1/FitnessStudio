package it.academy.fitness_studio.dao.api;

import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends PagingAndSortingRepository<UserEntity, UUID> {
    Optional<UserEntity> findByMail(String mail);
}
