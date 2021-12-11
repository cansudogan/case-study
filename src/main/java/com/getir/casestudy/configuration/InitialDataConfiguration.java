package com.getir.casestudy.configuration;

import com.getir.casestudy.domain.Role;
import com.getir.casestudy.domain.User;
import com.getir.casestudy.model.enums.ERole;
import com.getir.casestudy.repository.IRoleRepository;
import com.getir.casestudy.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitialDataConfiguration {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;

    public InitialDataConfiguration(IUserRepository userRepository, PasswordEncoder passwordEncoder, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {

        if (roleRepository.findByRole(ERole.ROLE_CUSTOMER).isEmpty()) {
            Role custRole = new Role();
            custRole.setRole(ERole.ROLE_CUSTOMER);
            roleRepository.save(custRole);
        }

        if (roleRepository.findByRole(ERole.ROLE_ADMIN).isEmpty()) {
            Role admRole = new Role();
            admRole.setRole(ERole.ROLE_ADMIN);
            roleRepository.save(admRole);
        }
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = User.builder().username("admin").email("admin@getir.com").password(passwordEncoder.encode("123456")).build();
            //User adminUser = new User("admin","admin@getir.com",passwordEncoder.encode("123456"));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByRole(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            adminUser.setRoles(roles);
            userRepository.save(adminUser);
        }

        if (!userRepository.existsByUsername("customer")) {
            User customerUser = User.builder().username("customer").email("customer@getir.com").password(passwordEncoder.encode("123456")).build();
            //User customerUser = new User("customer","customer@getir.com", passwordEncoder.encode("123456"));
            Set<Role> customerRoles = new HashSet<>();
            Role customerRole = roleRepository.findByRole(ERole.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            customerRoles.add(customerRole);
            customerUser.setRoles(customerRoles);
            userRepository.save(customerUser);
        }

    }
}
