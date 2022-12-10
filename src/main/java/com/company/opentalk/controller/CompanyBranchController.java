package com.company.opentalk.controller;


import com.company.opentalk.dto.CompanyBranchDto;
import com.company.opentalk.service.CompanyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/company-branch")
public class CompanyBranchController {

    @Autowired
    private CompanyBranchService service;

    @GetMapping
    public List<CompanyBranchDto> getBranchAll() {
        return service.getBranchAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyBranchDto> getBranchBYId(@PathVariable Integer id) {
        try {
            CompanyBranchDto companyBranchDto = service.getBranchById(id);
            return new ResponseEntity<CompanyBranchDto>(companyBranchDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<CompanyBranchDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CompanyBranchDto> addBranch(@Valid @RequestBody CompanyBranchDto companyBranchDto) {
        CompanyBranchDto companyBranchDto1 = service.saveBranch(companyBranchDto);
        return new ResponseEntity<>(companyBranchDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyBranchDto> updateBranch(@PathVariable Integer id,
                                                         @Valid @RequestBody CompanyBranchDto companyBranchDto) {
        CompanyBranchDto companyBranchDto1 = service.updateCompanyBranch(id, companyBranchDto);
        return new ResponseEntity<>(companyBranchDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable Integer id) {
        service.deleteBranch(id);
    }


}
