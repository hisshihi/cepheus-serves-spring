package com.example.web.cepheusservice.domain.dto;

import com.example.web.cepheusservice.domain.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private boolean inOrganization;
    private String orgName;
    private String orgAddress;
    private long orgINN;
    private String token;

    private Role role;

}
