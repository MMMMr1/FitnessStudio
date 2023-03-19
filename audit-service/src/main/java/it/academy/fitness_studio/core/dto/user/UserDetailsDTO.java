package it.academy.fitness_studio.core.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.core.validator.ValueOfEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@JsonIgnoreProperties
public class UserDetailsDTO
        implements UserDetails
{
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("fio")
    private String name;
    @ValueOfEnum(enumClass = UserRole.class)
    @JsonProperty("role")
    private String role;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(String uuid,
                          String mail,
                          String fio,
                          String role ) {
        this.uuid = uuid;
        this.mail = mail;
        this.name = fio;
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return mail;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
