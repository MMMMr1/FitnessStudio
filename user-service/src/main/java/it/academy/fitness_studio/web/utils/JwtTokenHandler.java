package it.academy.fitness_studio.web.utils;

import io.jsonwebtoken.*;
import it.academy.fitness_studio.configuration.properties.JWTProperty;
import it.academy.fitness_studio.core.dto.user.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
public class JwtTokenHandler {
    private final JWTProperty property;

    public JwtTokenHandler(JWTProperty property) {
        this.property = property;
    }

    public  String generateAccessToken(UserModel user) {
        Map<String, Object> claims = new HashMap<>();
        String commaSeparatedListOfAuthorities=  user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("authorities", commaSeparatedListOfAuthorities);
        claims.put("uuid", user.getUuid());
        claims.put("fio", user.getName());
        return generateAccessToken(claims , user.getMail());
    }

    public String generateAccessToken(Map<String, Object> claims, String subject ) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuer(property.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, property.getSecret())
                .compact();
    }    //извлечение имени пользователя из токена (внутри валидация токена)
    public  String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //извлечение authorities (внутри валидация токена)
    public String extractAuthorities(String token) {
        Function<Claims, String> claimsListFunction = claims -> (String)claims.get("authorities");
        return extractClaim(token, claimsListFunction);
    }

    private   <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private  Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token).getBody();
    }
    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public  boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
