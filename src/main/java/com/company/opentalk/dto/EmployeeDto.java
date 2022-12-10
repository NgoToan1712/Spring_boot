package com.company.opentalk.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component

public class EmployeeDto {

    //@NotNull
    private Integer employeeId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private LocalDate dateOfBirth;

    private String phone;

    @Email
    @NotNull
    private String email;

    private String branch;

    public EmployeeDto(Integer employeeId, String firstName, String lastName, String phone) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
