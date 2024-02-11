package com.example.web.cepheusservice.repositories.user;

import com.example.web.cepheusservice.TestDataUtil;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// unit tests
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

//    После каждого теста удаляем всё


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void isShouldCheckIfUserEmail() {

//        Give
        UserEntity user = TestDataUtil.createUserEntity();
        underTest.save(user);
//        when
        Optional<UserEntity> expected = underTest.findByEmail(user.getEmail());

//        then
        assertThat(expected).isPresent();

    }

    @Test
    void isShouldCheckIfUserId() {
        //        Give
        UserEntity user = TestDataUtil.createUserEntity();
        underTest.save(user);

//        when
        Optional<UserEntity> expected = underTest.findById(user.getId());

//        then
        assertThat(expected).isPresent();

        System.out.println(expected);
    }

    @Test
    void isShouldCheckIfUserEmailDoesNotExists() {

        // give
        String email = "hiss@gmail.com";

//        when
        Optional<UserEntity> expected = underTest.findByEmail(email);

//        then
        assertThat(expected).isNotPresent();

    }

    @Test
    void isShouldCheckIfUserIdDoesNotExists() {
//        give
        Long id = 3L;
//        when
        Optional<UserEntity> expected = underTest.findById(id);
//        then
        assertThat(expected).isNotPresent();

    }
}