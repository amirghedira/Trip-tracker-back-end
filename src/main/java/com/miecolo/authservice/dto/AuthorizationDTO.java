package com.miecolo.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDTO implements Serializable {
    private  String id;
    private  List<GrantedAuthority> authorities;
}
