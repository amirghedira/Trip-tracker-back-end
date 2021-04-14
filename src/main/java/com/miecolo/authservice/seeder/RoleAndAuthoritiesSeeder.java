package com.miecolo.authservice.seeder;

import com.miecolo.authservice.entity.Authority;
import com.miecolo.authservice.entity.Role;
import com.miecolo.authservice.repository.AuthorityRepository;
import com.miecolo.authservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleAndAuthoritiesSeeder {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        authoritiesSeed();
    }

    public void authoritiesSeed(){
         Authority authority = new Authority();
         authority.setName("FORUM-AUTHORITIES");
         authority = authorityRepository.save(authority);
         List<Authority> authorities = new ArrayList<>();
         authorities.add(authority);

         Role role = new Role();
         role.setName("USER");
         role.setAuthorities(authorities);
         roleRepository.save(role);
    }



}
