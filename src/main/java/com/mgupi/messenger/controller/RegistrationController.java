package com.mgupi.messenger.controller;

import com.mgupi.messenger.dto.registration.RegistrationRequest;
import com.mgupi.messenger.dto.registration.RegistrationResponse;
import com.mgupi.messenger.entity.CustomUser;
import com.mgupi.messenger.service.CustomUserDetailsService;
import com.mgupi.messenger.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public RegistrationResponse createNewUser(@RequestBody RegistrationRequest registrationRequest){
        CustomUser customUser = userService.registrationNewUser(registrationRequest);

        return new RegistrationResponse(customUser.getUsername(), customUser.getFirstName(), customUser.getLastName(),
                customUser.getPatronymic(), customUser.getPhoneNumber(), customUser.getDepartment().getName());
    }

}
