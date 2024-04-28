package com.example.web.cepheusservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq")
    private Long id;

    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String phone;

    private String address;

    private String password;

    private String token;

    private boolean inOrganization;
    private String orgName;
    private String orgAddress;
    private long orgINN;

    @JsonCreator
    public UserEntity(@JsonProperty("id") Long id) {
        this.id = id;
    }

//    Сообщаем, что это класс перечисления
    @Enumerated(EnumType.STRING)
    private Role role;

//    Роли для пользователя
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Устанавливаем, что для пользователя будет только 1 роль
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    //    Устанавливаем имя пользователя
    @Override
    public String getUsername() {
        return email;
    }

    // Устанваливаем, что срок действия аккаунта не истечёт
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Устанавливаем, что аккаунт не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

//    Устанавливаем, что пароль не истекает
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    Устанавливаем, что пользователь активен
    @Override
    public boolean isEnabled() {
        return true;
    }
}
