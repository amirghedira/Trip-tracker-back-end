package com.miecolo.authservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO implements Serializable {
    @NotBlank(message = "Username or email is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
