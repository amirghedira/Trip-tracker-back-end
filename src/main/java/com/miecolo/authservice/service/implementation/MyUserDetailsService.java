package com.miecolo.authservice.service.implementation;

import com.miecolo.authservice.entity.User;
import com.miecolo.authservice.exception.UnauthorizedException;
import com.miecolo.authservice.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByUsernameOrEmail(s,s);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        user = userRepository.findUserByActivatedIsTrueAndUsernameOrEmail(s,s);

        if (user.isEmpty())
            throw new UnauthorizedException("User is not activated");

        user = userRepository.findUserSuspendedIsFalseAndByUsernameOrEmail(s,s);

        if (user.isEmpty())
            throw new UnauthorizedException("User is suspended");


        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(r ->{
            r.getAuthorities().forEach(a -> {
                authorities.add((GrantedAuthority) a);
            });
        });
        return new org.springframework.security.core.userdetails.User(user.get().getId(),user.get().getPassword(),authorities);
    }
}
