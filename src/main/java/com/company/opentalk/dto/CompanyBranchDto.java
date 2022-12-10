package com.company.opentalk.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component

public class CompanyBranchDto {

    //@NotNull(message = "Branch Id cannot be blank")
    private Integer branchId;

    @NotNull(message = "Branch name cannot be blank")
    private String branchName;

    @NotNull(message = "Location cannot be blank")
    private String location;

    public CompanyBranchDto(String branchName, String location) {
        this.branchName = branchName;
        this.location = location;
    }

    public CompanyBranchDto(Integer branchId) {
        this.branchId = branchId;
    }
}
