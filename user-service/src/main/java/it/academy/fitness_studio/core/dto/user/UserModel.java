package it.academy.fitness_studio.core.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.jackson.CustomInstantConverter;
import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.core.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;


public class UserModel implements UserDetails {
    @JsonProperty("uuid")
    private UUID uuid;

    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_create")
    private  Instant dtCreate;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_update")
    private  Instant dtUpdate;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("fio")
    private String name;
    @JsonProperty("role")
    private UserRole role;
    @JsonProperty("status")
    private UserStatus status;

    public UserModel() {
    }

    public UserModel(UUID uuid,
                     Instant dtCreate,
                     Instant dtUpdate,
                     String mail,
                     String fio,
                     UserRole role,
                     UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.name = fio;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(role.toString()));
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
