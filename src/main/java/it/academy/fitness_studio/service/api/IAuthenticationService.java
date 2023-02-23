package it.academy.fitness_studio.service.api;


import it.academy.fitness_studio.core.dto.UserLoginDTO;
import it.academy.fitness_studio.core.dto.UserRegistrationDTO;

public interface IAuthenticationService {
    void create(UserRegistrationDTO user);
    void verify(String mail, String code);
    void login(UserLoginDTO user);
//
//    boolean exist(long id);
//
//    List<GenreModelDTO> get();
//
//    void delete(long id,long version);
//
//    void insert(GenreDTO genreDTO);
//
//    void update(long id, long version, GenreDTO genreDTO);
//    GenreCardModelDTO  get(long id);

}
