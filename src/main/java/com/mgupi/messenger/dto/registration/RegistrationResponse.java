package com.mgupi.messenger.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String department;

}
