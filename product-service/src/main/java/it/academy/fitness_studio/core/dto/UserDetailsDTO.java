package it.academy.fitness_studio.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserDetailsDTO implements UserDetails{
    private String uuid;
    private String mail;
    private String name;
    private String role;
    public UserDetailsDTO() {    }

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
         return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(role.toString()));
    }
    @Override
    public String getPassword() {
        return null;
    }
    @Override
    public String getUsername() {
        return mail;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
