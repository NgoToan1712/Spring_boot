package com.company.opentalk.controller;

import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.service.EmployeePagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee-paging")
public class EmployeePagingController {

    @Autowired
    private EmployeePagingService employeePagingService;

    @GetMapping()
    public ResponseEntity<?> getAllEmployee(@RequestParam("pageNo") Integer pageNo,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("sortBy") String sortBy) {
        List<EmployeeDto> list = employeePagingService.getAllEmployees(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<?> getAllEmployee1(@RequestParam("pageNo") Integer pageNo,
                                             @RequestParam("pageSize") Integer pageSize) {
        List<EmployeeDto> list = employeePagingService.getAllEmployees1(pageNo, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<?> getAllEmployee2(@RequestParam("sortBy1") String sortBy1,
                                             @RequestParam("sortBy2") String sortBy2) {
        List<EmployeeDto> list = employeePagingService.getAllEmployees2(sortBy1, sortBy2);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getByFirstName")
    public ResponseEntity<?> etAllEmployeeByFirstName(@RequestParam("pageNo") Integer pageNo,
                                                      @RequestParam("pageSize") Integer pageSize,
                                                      @RequestParam("sortBy") String sortBy,
                                                      @RequestParam("firstName") String firstName) {
        List<EmployeeDto> list = employeePagingService.getAllEmployeeByFirstName(pageNo, pageSize, firstName,sortBy);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
