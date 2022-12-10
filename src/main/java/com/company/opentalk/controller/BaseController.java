package com.company.opentalk.controller;

import com.company.opentalk.dto.CompanyBranchDto;
import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.repository.TopicRepository;
import com.company.opentalk.service.CompanyBranchService;
import com.company.opentalk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BaseController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyBranchService companyBranchService;

    @RequestMapping("/test")
    public String index(Model model) {
        CompanyBranchDto companyBranchDto = companyBranchService.getBranchById(1);
        model.addAttribute("company", companyBranchDto);
        List<EmployeeDto> employeeDtos = employeeService.getEmployeeAll();
        model.addAttribute("employees", employeeDtos);
        return "index";
    }


}
