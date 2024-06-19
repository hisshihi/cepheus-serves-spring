package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.UserDto;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.services.JwtService;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Mapper<UserEntity, UserDto> mapper;
    private final UserServise userServise;
    private final JwtService jwtService;

    @PostMapping(path = "/users/{token}")
    public ResponseEntity<UserDto> getUsers(@PathVariable String token) {
        String userEmail = jwtService.extractUseremail(token);
        UserEntity userEntity = userServise.findUserByEmail(userEmail);
        UserDto userDto = mapper.mapTo(userEntity);

//        UserEntity userEntity = mapper.mapFrom(userDto);
//        System.out.println(userEntity);
        return ResponseEntity.ok(userDto);
    }

    //    Получаения всех пользователей
    @GetMapping(path = "/users")
    public List<UserDto> listUsers() {
        List<UserEntity> userEntityList = userServise.findAll();
        return userEntityList.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    //    Поиск пользователя по id
    @GetMapping(path = "/users/name")
    public ResponseEntity<UserDto> findUser(Principal principal) {

        UserEntity userId = userServise.findUserByEmail(principal.getName());

        Optional<UserEntity> findUser = userServise.findUserById(userId.getId());
        return findUser.map(userEntity -> {
            UserDto userDto = mapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Обновление роли пользователя
    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> variableUpdateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        boolean userExists = userServise.isExists(id);
        if (!userExists) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        UserEntity userEntity = mapper.mapFrom(userDto);
        UserEntity updateUser = userServise.partialUpdate(id, userEntity);
        return new ResponseEntity<>(mapper.mapTo(updateUser), HttpStatus.OK);
    }


    //    Удаление пользователя
    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable("id") Long id) {
        Optional<UserEntity> user = userServise.findUserById(id);
        user.get();
        System.out.println(user.get());
        userServise.delete(id);
        return new ResponseEntity(user.get().getToken(), HttpStatus.OK);
    }

//    Обновиление данных польователя
    @PatchMapping(path = "/users/editing")
    public ResponseEntity<String> pathVariableAllUserData(Principal principal, @RequestBody UserDto userDto) {

        UserEntity findUser = userServise.findUserByEmail(principal.getName());

        System.out.println(findUser);

        boolean userExists = userServise.isExists(findUser.getId());
        if (!userExists) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        UserEntity user = mapper.mapFrom(userDto);
        userServise.partialUpdateAllDataUser(findUser.getId(), user);
        return ResponseEntity.ok().build();
    }

//    Поиск пользователя по email
    @GetMapping(path = "/user/{email}")
    public ResponseEntity<List<UserEntity>> getUserListByEmail(@PathVariable String email) {
        List<UserEntity> user = userServise.findAllByEamil(email);
        return ResponseEntity.ok(user);
    }

}
