package it.academy.fitness_studio.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
@Entity
@Table(schema = "app",name = "users")
@SecondaryTable(
        schema = "app",name = "verification",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
public class UserEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;
    @Column(name = "dtcreate")
    private Instant dtCreate;
    @Version
    @Column(name = "dtupdate")
    private Instant dtUpdate;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinTable(schema = "app",
            name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns =
            @JoinColumn(name  = "role_id")
    )
    private RoleEntity role;
    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinTable(schema = "app",
            name = "user_status",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns =
            @JoinColumn(name  = "status_id")
    )
    private StatusEntity status;
    @Column(name = "code", table= "verification")
    private String code;
    private String password;
    public UserEntity(){

    }
    public UserEntity(UUID uuid,
                      Instant dtCreate,
                      Instant dtUpdate,
                      String mail,
                      String fio,
                      RoleEntity role,
                      StatusEntity status,
                      String password) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public UserEntity(String mail, String fio, RoleEntity role, StatusEntity status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
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
    public String getFio() {
        return fio;
    }
    public RoleEntity getRole() {
        return role;
    }
    public StatusEntity getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
    public void setRole(RoleEntity role) {
        this.role = role;
    }
    public void setStatus(StatusEntity status) {
        this.status = status;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UUID setUuid(UUID uuid) {
        this.uuid = uuid;
        return uuid;
    }

    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(Instant dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
}
