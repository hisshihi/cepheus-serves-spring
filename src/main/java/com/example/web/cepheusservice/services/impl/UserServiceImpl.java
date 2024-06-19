package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.UserServise;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServise {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow();
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity partialUpdate(Long id, UserEntity userEntity) {
        userEntity.setId(id);
        return userRepository.findById(id).map(exsistingUser -> {
            Optional.ofNullable(userEntity.getRole()).ifPresent(exsistingUser::setRole);
            return userRepository.save(exsistingUser);
        }).orElseThrow(() -> new RuntimeException("Данный пользователь не сущесвтует"));
    }

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void partialUpdateAllDataUser(Long id, UserEntity userDto) {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(existingUser.getId());
        updatedUser.setFirstname(userDto.getFirstname() != null ? userDto.getFirstname() : existingUser.getFirstname());
        updatedUser.setLastname(userDto.getLastname() != null ? userDto.getLastname() : existingUser.getLastname());
        updatedUser.setEmail(userDto.getEmail() != null ? userDto.getEmail() : existingUser.getEmail());
        updatedUser.setPhone(userDto.getPhone() != null ? userDto.getPhone() : existingUser.getPhone());
        updatedUser.setAddress(userDto.getAddress() != null ? userDto.getAddress() : existingUser.getAddress());
        updatedUser.setOrgINN(userDto.getOrgINN() != 0 ? userDto.getOrgINN() : existingUser.getOrgINN());
        updatedUser.setOrgName(userDto.getOrgName() != null ? userDto.getOrgName() : existingUser.getOrgName());
        updatedUser.setOrgAddress(userDto.getOrgAddress() != null ? userDto.getOrgAddress() : existingUser.getOrgAddress());

        /// Only update password if it's not null
        if (userDto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            updatedUser.setPassword(encodedPassword);
        } else {
            updatedUser.setPassword(existingUser.getPassword());
        }

        // Only update roles if they're not null and different from existing user's roles
        if (userDto.getRole() != null && !userDto.getRole().equals(existingUser.getRole())) {
            updatedUser.setRole(userDto.getRole());
        } else {
            updatedUser.setRole(existingUser.getRole());
        }

        if (userDto.isInOrganization()) {
            updatedUser.setInOrganization(true);
        } else {
            updatedUser.setInOrganization(existingUser.isInOrganization());
        }

        userRepository.save(updatedUser);
    }

    @Override
    public List<UserEntity> findAllByEamil(String email) {
        List<UserEntity> users = userRepository.findByEmailContainingIgnoreCase(email);
        return users;
    }


}
