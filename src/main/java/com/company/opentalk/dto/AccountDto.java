package com.company.opentalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    //@NotNull
    private Integer employeeId;

    @NotNull(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Last name cannot be blank")
    private String lastName;

    private String dateOfBirth;
    private String phone;

    @Email(message = "Malformed mail Name@domain")
    @NotNull(message = "Email cannot be blank")
    private String email;

    private String branch;

    private String password;

    private boolean enabled;

    public AccountDto(String firstName, String lastName, String dateOfBirth, String phone,
                      String email, String branch, String password, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
        this.password = password;
        this.enabled = enabled;
    }

    public AccountDto(String firstName, String lastName, String dateOfBirth, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
    }

    public AccountDto(String firstName, String lastName, String dateOfBirth,
                      String phone, String branch, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.branch = branch;
        this.enabled = enabled;
    }
}
