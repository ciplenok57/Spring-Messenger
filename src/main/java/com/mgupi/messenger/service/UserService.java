package com.mgupi.messenger.service;

import com.mgupi.messenger.enums.Role;
import com.mgupi.messenger.dto.registration.RegistrationRequest;
import com.mgupi.messenger.entity.CustomUser;
import com.mgupi.messenger.repository.CustomUserRepository;
import com.mgupi.messenger.repository.DepartmentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final CustomUserRepository customUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;

    public UserService(CustomUserRepository customUserRepository, PasswordEncoder passwordEncoder, DepartmentRepository departmentRepository) {
        this.customUserRepository = customUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepository = departmentRepository;
    }


    public CustomUser registrationNewUser(RegistrationRequest registrationRequest){
        // add transaction
        CustomUser customUser = new CustomUser();

        customUser.setUsername(registrationRequest.getUsername());
        customUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        customUser.setRole(Role.USER);
        customUser.setFirstName(registrationRequest.getFirstName());
        customUser.setLastName(registrationRequest.getLastName());
        customUser.setPatronymic(registrationRequest.getPatronymic());
        customUser.setPhoneNumber(registrationRequest.getPhoneNumber());
        customUser.setDepartment(departmentRepository.findByName(registrationRequest.getDepartment()));

        customUserRepository.save(customUser);
        return customUser;
    }

    public CustomUser findUserByLoginAndPassword(String username, String password){
        CustomUser user = customUserRepository.findByUsername(username);
        System.out.println(user);
        if (user == null){
            return null;
        }

        if(user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }

    }
}
