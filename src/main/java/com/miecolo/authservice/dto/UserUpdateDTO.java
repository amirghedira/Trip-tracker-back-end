package com.miecolo.authservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String avatar;
    private Date birthDate;
    private String address;
    private String phoneNumber;
}
