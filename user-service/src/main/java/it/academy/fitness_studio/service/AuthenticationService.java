package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserLoginDTO;
import it.academy.fitness_studio.core.dto.user.UserRegistrationDTO;
import it.academy.fitness_studio.core.exception.UserNotFoundException;
import it.academy.fitness_studio.core.exception.ValidationUserException;
import it.academy.fitness_studio.dao.api.IAuthenticationDao;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import it.academy.fitness_studio.service.api.IAuthenticationService;
import it.academy.fitness_studio.service.api.IUserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class AuthenticationService implements IAuthenticationService  {
    public static final String HTTP_MAIL_SERVICE_8080_API_V_1_MAIL_VERIFICATION = "http://mail-service:8080/api/v1/mail/verification";
    private final IAuthenticationDao dao;
    private final IUserService service;
    private ConversionService conversionService;
    private BCryptPasswordEncoder encoder;

    public AuthenticationService(IAuthenticationDao dao,
                                 IUserService service,
                                 ConversionService conversionService,
                                 BCryptPasswordEncoder encoder
    ) {
        this.dao = dao;
        this.service = service;
        this.conversionService = conversionService;
        this.encoder = encoder;
    }
    @Override
    public void create(UserRegistrationDTO user) {
        service.create(new UserDTO(user.getMail(), user.getFio(), user.getPassword()));
        UserEntity userEntity = find(user.getMail());
        String code = UUID.randomUUID().toString();
        userEntity.setCode(code);
        dao.save(userEntity);
        sendMessage(user.getMail(),code);
    }

    @Override
    public void verify(String code,String mail) {
        UserEntity userEntity = find(mail);
        if(code.equals(userEntity.getCode())){
            userEntity.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            userEntity.setCode(null);
            dao.save(userEntity);
        } else throw new ValidationUserException("Incorrect mail and code");
    }
    @Override
    public UserModel login(@Validated UserLoginDTO user) {
        UserEntity userEntity = find(user.getMail());
        if(!encoder.matches(user.getPassword(),userEntity.getPassword())){
            throw new ValidationUserException("Incorrect mail and password");
        }
        return conversionService.convert(userEntity,UserModel.class);
    }
    @Override
    public UserModel getUser(String mail) {
        UserEntity userEntity = dao.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("There is no user with such id"));
        if (!conversionService.canConvert(UserEntity.class, UserModel.class)) {
            throw new IllegalStateException("Can not convert UserEntity.class to UserModel.class");
        }
        return conversionService.convert(userEntity, UserModel.class);
    }
    private UserEntity find(String mail){
        return dao.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("User with this mail is not registered"));
    }
    private void sendMessage(String to, String code ){
        try {
            JSONObject object =new JSONObject();
            object.put("to", to);
            object.put("subject", "Активируйте свою учетную запись в Thyme ");
            object.put("text",code);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HTTP_MAIL_SERVICE_8080_API_V_1_MAIL_VERIFICATION))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();
//            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            httpClient.sendAsync(request,HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .exceptionally(e -> "Exception: "+ e);
        } catch (JSONException e
//                 | InterruptedException | IOException e
        ) {
            throw new RuntimeException(e);
        }
    }
}
