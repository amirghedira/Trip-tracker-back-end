package com.miecolo.authservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class User implements Serializable {

    @Id
    private String id;
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    private String username;
    @NotBlank(message = "Username is mandatory")
    private String firstName;
    @NotBlank(message = "Username is mandatory")
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    @JsonIgnore
    private Boolean activated = false;
    @JsonIgnore
    private Boolean deleted = false;
    @JsonIgnore
    private Boolean suspended = false;

    @DBRef
    private List<Role> roles = new ArrayList<>();

}
