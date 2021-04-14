package com.miecolo.authservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Document
public class Role implements Serializable {

    @Id
    private String id;
    @NotBlank(message = "Role name is mandatory")
    private String name;
    @DBRef
    List<Authority> authorities = new ArrayList<>();

}
