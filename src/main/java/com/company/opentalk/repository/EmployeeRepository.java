package com.company.opentalk.repository;

import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.dto.IEmployeeCount;
import com.company.opentalk.entity.CompanyBranch;
import com.company.opentalk.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Phụ thuộc vào CSDL thì thêm nativeQuery=true
    @Query(value = "select * from employee where first_name = ?1", nativeQuery = true)
    List<Employee> findEmployeeBy(String firstName);

    @Query(value = "select e.email from Employee e ")
    List<String> getEmail();

    @Query(value = "select e from Employee e where e.email=?1")
    Employee findByEmail(String email);

    List<Employee> findAllByCompanyBranch(CompanyBranch companyBranch);
    Slice<Employee> findByFirstName(String firstName, Pageable pageable);

    @Query(value = "select  e.companyBranch.branchName as branchName ,"
            + "COUNT(e.companyBranch.branchName) as totalEmployee "
            + "from Employee e group by e.companyBranch.branchName"
            + " order by e.companyBranch.branchName asc ")
    List<IEmployeeCount> countTotalEmployeeByBranch();

    @Query(value = "select  new com.company.opentalk.dto.EmployeeDto" +
            "(e.employeeId,e.firstName,e.lastName,e.phone) from Employee e where  e.companyBranch.branchName= :branch")
    List<EmployeeDto> getEmployeeByBranch(@Param("branch") String Branch);
}
