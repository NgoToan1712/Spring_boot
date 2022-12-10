package com.company.opentalk.entity;


import com.company.opentalk.dto.CompanyBranchDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company_branch")
@Builder

public class CompanyBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "companyBranch", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany(mappedBy = "companyBranches")
    private List<Topic> topics = new ArrayList<>();

    public CompanyBranch(Integer branchId, String branchName, String location) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.location = location;
    }

    public CompanyBranchDto toDto() {
        return CompanyBranchDto.builder()
                .branchId(this.getBranchId())
                .branchName(this.getBranchName())
                .location(this.location)
                .build();

    }
// cascade:
    // - all
    // DETACH nếu dt cha bị tách khỏi persistence context thì đối dt tham chiếu cũng bị tách
    //MERGE hợp
    //PERSIST duy trì
    //REFRESH
    //REMOVE

    //không khai báo FetchType là Lazy
}
