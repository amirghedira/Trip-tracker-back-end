package com.miecolo.authservice.service;

import com.miecolo.authservice.dto.UserLoginDTO;
import com.miecolo.authservice.dto.UserRegisterDTO;
import com.miecolo.authservice.exception.UsernameOrEmailExistException;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<?> login(UserLoginDTO userLoginDTO);
    public ResponseEntity<?> register(UserRegisterDTO userRegisterDTO) throws UsernameOrEmailExistException;
    public ResponseEntity<?> usernameOrEmailExist(String usernameOrEmail);
}
