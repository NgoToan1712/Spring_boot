package com.company.opentalk.service;

import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.entity.Employee;
import com.company.opentalk.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeePagingService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Employee> page = employeeRepository.findAll(paging);
        if (page.hasContent()) {
            return page.getContent().stream().map(Employee::toDto).collect(Collectors.toList());
        } else {
            return new ArrayList<EmployeeDto>();
        }
    }

    public List<EmployeeDto> getAllEmployees1(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Employee> page = employeeRepository.findAll(paging);
        if (page.hasContent()) {
            return page.getContent().stream().map(Employee::toDto).collect(Collectors.toList());
        } else {
            return new ArrayList<EmployeeDto>();
        }
    }

    public List<EmployeeDto> getAllEmployees2(String sortBy1, String sortBy2) {
        Sort sort1 = Sort.by(sortBy1);
        Sort sort2 = Sort.by(sortBy2).ascending();
        Sort groupBySort = sort1.and(sort2);
        return ((List<Employee>) employeeRepository.findAll(groupBySort))
                .stream().map(Employee::toDto).collect(Collectors.toList());

    }

    public List<EmployeeDto> getAllEmployeeByFirstName(Integer pageNo, Integer pageSize,
                                                       String firstName, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Slice<Employee> sliceResult = employeeRepository.findByFirstName(firstName, paging);
        return sliceResult.getContent().stream().map(Employee::toDto).collect(Collectors.toList());
    }
}
