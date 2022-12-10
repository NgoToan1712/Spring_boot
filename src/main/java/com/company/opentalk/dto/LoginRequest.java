package com.company.opentalk.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "Mail cannot be blank")
    @Email(message = "Malformed mail Name@domain")
    private String email;

    @NotNull(message = "Password cannot be blank")
    private String password;
}
