package com.miecolo.authservice.controller;

import com.miecolo.authservice.dto.AuthorizationDTO;
import com.miecolo.authservice.dto.UserLoginDTO;
import com.miecolo.authservice.dto.UserRegisterDTO;
import com.miecolo.authservice.exception.UsernameOrEmailExistException;
import com.miecolo.authservice.service.implementation.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    AuthServiceImpl authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) throws Exception {
        return authService.login(userLoginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) throws UsernameOrEmailExistException {
        return authService.register(userRegisterDTO);
    }

    @GetMapping("/exist/{usernameOrEmail}")
    public ResponseEntity<?> existUsernameOrEmail(@PathVariable String usernameOrEmail) {
        return authService.usernameOrEmailExist(usernameOrEmail);
    }


    @GetMapping("/authorize")
    public AuthorizationDTO authorize(){
        AuthorizationDTO authorizationDTO = new AuthorizationDTO(SecurityContextHolder.getContext().getAuthentication().getName(), (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return authorizationDTO;
    }
}