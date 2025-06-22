package com.miniBOM.pojo;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Long id;//->ID
    private String name;//->Name
    @JsonIgnore
    private String password;//->UserPassword
    private String email;//->Email
    private String phoneNumber;//->PhoneNumber
}
