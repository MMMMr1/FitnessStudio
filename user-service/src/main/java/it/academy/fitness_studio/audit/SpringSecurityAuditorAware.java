//package it.academy.fitness_studio.audit;
//
//import it.academy.fitness_studio.core.dto.user.UserModel;
//import it.academy.fitness_studio.service.UserHolder;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
//public class SpringSecurityAuditorAware implements AuditorAware<UserModel > {
//    @Override
//    public Optional<UserModel> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserHolder userHolder = new UserHolder();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//
//        return  Optional.of(userHolder.getUser()) ;
//    }
//}
