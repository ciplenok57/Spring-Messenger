package com.mgupi.messenger.dto.registration;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String department;

}
