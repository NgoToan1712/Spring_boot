package com.company.opentalk.controller;

import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.dto.IEmployeeCount;
import com.company.opentalk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/all")
    public List<EmployeeDto> getAll() {
        return service.getEmployeeAll();
    }


    @GetMapping()
    public EmployeeDto getById(@RequestParam("id") Integer id) {
        return service.getById(id);
    }

    @GetMapping("/{firstName}")
    public List<EmployeeDto> getByLastName(@PathVariable("firstName") String lastName) {
        return service.getByEmployee(lastName);
    }

    @GetMapping("/count")
    public List<IEmployeeCount> getCountEmployeeByBranch(){
        return service.getCountEmployee();
    }

    @GetMapping("/branch")
    public List<EmployeeDto> getCountEmployeeByBranch(@RequestParam("branchName") String branchName){
        return service.getEmployeeByBranch(branchName);
    }
    // có 4 cách nhận request data trong controller
    //  request param: các tham số cách nhau bởi &
    // lấy ra: @RequestParam("name"), thuộc tính không bắt buộc thì để required=false

    //@PathVariable {},
    //@RequestBody: data thường ở dạng json, form-data
//@Header
}

