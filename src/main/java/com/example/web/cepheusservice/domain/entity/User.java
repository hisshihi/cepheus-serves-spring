package com.example.web.cepheusservice.domain.entity;

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
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq")
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;

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
