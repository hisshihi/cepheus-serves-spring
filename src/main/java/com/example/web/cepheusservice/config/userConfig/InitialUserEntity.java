package com.example.web.cepheusservice.config.userConfig;

import com.example.web.cepheusservice.domain.entity.Role;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class InitialUserEntity implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        UserEntity admin = new UserEntity();
        admin.setRole(Role.ADMIN);
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("123"));

        UserEntity user = new UserEntity();
        user.setFirstname("Денис");
        user.setLastname("Кожухин");
        user.setEmail("outrun@gmail.com");
        user.setPhone("+7 (908) 837-93-82");
        user.setAddress("Карла Маркса 111А");
        user.setInOrganization(true);
        user.setOrgAddress("Гоголя 128");
        user.setOrgName("outrunInc");
        user.setRole(Role.USER);
        user.setOrgINN(234234234234L);
        user.setPassword(passwordEncoder.encode("123"));

        if (admin.getEmail().equals(userRepository.findByEmail(admin.getEmail())) || user.getEmail().equals(userRepository.findByEmail(user.getEmail()))) {
            userRepository.saveAll(List.of(admin, user));
        }


    }
}
