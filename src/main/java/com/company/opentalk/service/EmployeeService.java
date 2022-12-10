package com.company.opentalk.service;

import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.dto.IEmployeeCount;
import com.company.opentalk.entity.Employee;
import com.company.opentalk.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> getEmployeeAll() {
        return employeeRepository.findAll().stream().map(Employee::toDto).collect(Collectors.toList());
    }

    public EmployeeDto getById(Integer id) {
        return employeeRepository.findById(id).get().toDto();
    }

    public List<EmployeeDto> getByEmployee(String firstName) {
        return employeeRepository.findEmployeeBy(firstName).stream()
                .map(item -> item.toDto()).collect(Collectors.toList());
    }

    public List<IEmployeeCount> getCountEmployee() {
        return employeeRepository.countTotalEmployeeByBranch();
    }

    public List<EmployeeDto> getEmployeeByBranch(String branch) {
        return employeeRepository.getEmployeeByBranch(branch);
    }

//    @Async
//    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
//        long start, end;
//        start = System.nanoTime();
//        Employee employee = mapper.map(employeeDto, Employee.class);
//        CompanyBranch companyBranch = branchRepository.findByranchName(employeeDto.getBranch());
//        employee.setCompanyBranch(companyBranch);
//        try {
//            employeeRepository.save(employee);
//            mailService.sendEmailCreateEmployee(employeeDto);
//            return employeeRepository.findById(employee.getEmployeeId()).get().toDto();
//        } catch (Exception e) {
//            logger.error("Unable to create employee");
//        }
//        end = System.nanoTime();
//        System.out.println("Program execution time: " + (end - start));
//        return null;
//    }
//
//    public void deleteEmployee(Integer id) {
//        employeeRepository.deleteById(id);
//    }
//

}
