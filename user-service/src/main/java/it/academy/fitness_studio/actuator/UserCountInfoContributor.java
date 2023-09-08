package it.academy.fitness_studio.actuator;

import it.academy.fitness_studio.dao.api.UserDao;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserCountInfoContributor implements InfoContributor {
    private UserDao userDao;

    public UserCountInfoContributor(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void contribute(Info.Builder builder) {
        long userCount = userDao.count();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("count", userCount);
        builder.withDetail("user-statistics", userMap);

    }
}
