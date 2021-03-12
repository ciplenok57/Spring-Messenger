package com.mgupi.messenger.controller;

import com.mgupi.messenger.dto.auth.AuthRequest;
import com.mgupi.messenger.dto.auth.AuthResponse;
import com.mgupi.messenger.entity.CustomUser;
import com.mgupi.messenger.service.JWTUtil;
import com.mgupi.messenger.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    private final JWTUtil jwtTokenUtil;
    private final UserService userService;

    public AuthenticationController(JWTUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) {

        CustomUser user = userService.findUserByLoginAndPassword(authRequest.getUsername(), authRequest.getPassword());

        if(user != null){
            return new AuthResponse(jwtTokenUtil.generateToken(user.getUsername()));
        }else{
            return new AuthResponse("None");
        }

    }

}
