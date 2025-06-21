package com.miniBOM.pojo;

import javax.validation.constraints.Email;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Data
public class User {
    private Long id;//->ID
    private String name;//->Name
    @JsonIgnore
    private String password;//->UserPassword
    private String email;//->Email
    private String phoneNumber;//->PhoneNumber
}
