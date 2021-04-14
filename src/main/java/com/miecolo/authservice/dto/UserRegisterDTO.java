package com.miecolo.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDTO {
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    private String username;
    @NotBlank(message = "Username is mandatory")
    private String firstName;
    @NotBlank(message = "Username is mandatory")
    private String lastName;
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;
    private String avatar;
    @NotNull(message = "Birth date is mandatory")
    private Date birthDate;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email in mandatory")
    private String email;
    private String address;
    private String phoneNumber;
    private String quote;
    private Integer score;
}
