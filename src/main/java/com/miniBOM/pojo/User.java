package com.miniBOM.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;//->ID
    private String name;//->Name
    private String password;//->UserPassword
    private String email;//->Email
    private String phoneNumber;//->PhoneNumber
}
