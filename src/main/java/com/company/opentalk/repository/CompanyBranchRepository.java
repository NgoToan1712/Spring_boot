package com.company.opentalk.repository;

import com.company.opentalk.entity.CompanyBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyBranchRepository extends JpaRepository<CompanyBranch, Integer> {
    @Query("select b from CompanyBranch b where b.branchId= :id")
    CompanyBranch findCompanyBranchBy(@Param("id") Integer id);

    @Query("select  b from CompanyBranch b where b.branchName= ?1")
    CompanyBranch findByranchName(String name);

    @Query("select new com.company.opentalk.entity.CompanyBranch (c.branchId,c.branchName,c.location)" +
            " from CompanyBranch c order by c.branchName asc ")
    List<CompanyBranch> getList();
}
