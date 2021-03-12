package com.mgupi.messenger.service;

import com.mgupi.messenger.dto.registration.RegistrationRequest;
import com.mgupi.messenger.entity.CustomUser;
import com.mgupi.messenger.repository.CustomUserRepository;
import com.mgupi.messenger.repository.DepartmentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        CustomUser myUser= customUserRepository.findByUsername(userName);

        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }

        System.out.println(myUser.getUsername());

        UserDetails user = User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getRole().name())
                .build();
        return user;
    }

}
