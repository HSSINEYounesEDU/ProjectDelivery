package com.BlogsProject.Authentication.Controller.AuthenticationServices;

import com.BlogsProject.Authentication.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String firstname;
    private String lastname;
    private Role role;
    private String email;
    private String phonenumber;
    private String address;
}
