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
public class AuthenticationResponse {

    private String activeToken;
    private String uid;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private Role role;
}
