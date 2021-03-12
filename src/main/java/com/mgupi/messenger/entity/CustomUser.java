package com.mgupi.messenger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mgupi.messenger.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String username;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;


}
