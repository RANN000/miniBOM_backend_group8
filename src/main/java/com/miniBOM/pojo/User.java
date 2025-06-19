package com.miniBOM.pojo;

import jakarta.validation.constraints.Email;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Data
public class User {
    private Integer id;//->ID
    private String name;//->Name
    @JsonIgnore
    private String password;//->UserPassword
    @Email
    private String email;//->Email
    private String phoneNumber;//->PhoneNumber
}
