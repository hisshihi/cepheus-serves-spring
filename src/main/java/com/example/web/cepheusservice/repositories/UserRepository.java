package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Ищем пользователя по email
//    Указываем findBy а дальше добавляем то, по чему будем искать в данном случаем по полю email
    Optional<User> findByEmail(String email);

}
