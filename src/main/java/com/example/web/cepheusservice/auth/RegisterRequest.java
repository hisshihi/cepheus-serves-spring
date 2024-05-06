package com.example.web.cepheusservice.auth;

import com.example.web.cepheusservice.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String token;
    private String address;

    private String password;
    private boolean inOrganization;
    private String orgName;
    private String orgAddress;
    private long orgINN;
    private Role role;

}
