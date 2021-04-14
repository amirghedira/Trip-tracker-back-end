package com.miecolo.authservice.service.implementation;

import com.miecolo.authservice.dto.LoginResponseDTO;
import com.miecolo.authservice.dto.UserLoginDTO;
import com.miecolo.authservice.dto.UserRegisterDTO;
import com.miecolo.authservice.entity.User;
import com.miecolo.authservice.exception.UsernameOrEmailExistException;
import com.miecolo.authservice.mapper.UserRegistrationMapper;
import com.miecolo.authservice.repository.RoleRepository;
import com.miecolo.authservice.repository.UserRepository;
import com.miecolo.authservice.service.AuthService;
import com.miecolo.authservice.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper mapper;


    public ResponseEntity<?> login(UserLoginDTO userLoginDTO){
        Authentication authentication = null;

        try {
             authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),userLoginDTO.getPassword()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        }


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(authentication.getName(),"",authentication.getAuthorities());

        Map<String,Object> claims = new HashMap<>();
        Optional<User> user = userRepository.findById(userDetails.getUsername());
        claims.put("name",user.get().getFirstName()+user.get().getLastName());
        claims.put("username",user.get().getUsername());
        claims.put("email",user.get().getEmail());
        claims.put("authorities",userDetails.getAuthorities());

        String accessToken = jwtUtils.doGenerateToken(claims,user.get().getId());
        String refreshToken = jwtUtils.doGenerateRefreshToken(claims,user.get().getId());

        return ResponseEntity.ok(new LoginResponseDTO(accessToken,refreshToken,user.get()));
    }

    @Override
    public ResponseEntity<?> register(UserRegisterDTO userRegisterDTO) throws UsernameOrEmailExistException {

        Boolean exists = userRepository.existsByUsername(userRegisterDTO.getUsername());

        if (exists)
            throw new UsernameOrEmailExistException("Username already exist");

        exists = userRepository.existsByEmail(userRegisterDTO.getEmail());

        if (exists)
            throw new UsernameOrEmailExistException("Email already exist");

        User user = mapper.map(userRegisterDTO,User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActivated(true);
        user = userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> usernameOrEmailExist(String usernameOrEmail)  {
        Boolean exists = userRepository.existsByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        return ResponseEntity.ok().body(exists);
    }

}
