package com.company.opentalk.entity;

import com.company.opentalk.dto.EmployeeDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;
    @Column(name = "first_name", length = 30)
    private String firstName;
    @Column(name = "last_name", length = 30)
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "phone", length = 10)
    private String phone;
    @Column(name = "email")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "branch_id")
    private CompanyBranch companyBranch;

    @ManyToMany(mappedBy = "organizers")
    private List<Topic> topicsOrganizers = new ArrayList<>();

    @ManyToMany(mappedBy = "participants")
    private List<Topic> topicsParticipants = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public EmployeeDto toDto() {
        String branch = this.getCompanyBranch().getBranchName();
        return EmployeeDto.builder()
                .employeeId(this.employeeId)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .dateOfBirth(this.dateOfBirth)
                .phone(this.phone)
                .email(this.email)
                .branch(branch)
                .build();

    }

    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String phone, String email,
                    CompanyBranch companyBranch, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.companyBranch = companyBranch;
        this.user = user;
    }
}
