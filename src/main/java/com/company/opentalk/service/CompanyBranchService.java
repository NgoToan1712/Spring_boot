package com.company.opentalk.service;

import com.company.opentalk.dto.CompanyBranchDto;
import com.company.opentalk.entity.CompanyBranch;
import com.company.opentalk.repository.CompanyBranchRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyBranchService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CompanyBranchRepository companyRepository;
    @Autowired
    private ModelMapper modelMapper;

//        public List<CompanyBranchDto> getBranchAll() {
//        return companyRepository.findAll().stream().map(item -> item.toDto()).collect(Collectors.toList());
//    }
    public List<CompanyBranchDto> getBranchAll() {
        return companyRepository.getList().stream().map(CompanyBranch::toDto).collect(Collectors.toList());
    }

    public CompanyBranchDto getBranchById(Integer id) {
        CompanyBranch companyBranch = companyRepository.findCompanyBranchBy(id);
        CompanyBranchDto companyBranchDto = modelMapper.map(companyBranch, CompanyBranchDto.class);
        return companyBranchDto;
    }

    public CompanyBranchDto saveBranch(CompanyBranchDto companyBranchDto) {
        CompanyBranch companyBranch1 = companyRepository.findByranchName(companyBranchDto.getBranchName());
        if (companyBranch1 != null) {
            logger.error("Company branch already exists");
        } else {
            CompanyBranch companyBranch = modelMapper.map(companyBranchDto, CompanyBranch.class);
            try {
                companyRepository.save(companyBranch);
                return companyRepository.findByranchName(companyBranchDto.getBranchName()).toDto();
            } catch (DataAccessException e) {
                logger.error("Unable to create company branch");
            }
        }
        return null;
    }

    public CompanyBranchDto updateCompanyBranch(Integer id, CompanyBranchDto companyBranchDto) {
        try {
            CompanyBranch companyBranch = companyRepository.findById(id).get();
            try {
                CompanyBranch companyBranch1 = modelMapper.map(companyBranchDto, CompanyBranch.class);
                companyRepository.save(companyBranch1);
                return companyRepository.findCompanyBranchBy(id).toDto();
            } catch (DataAccessException e) {
                logger.error("Unable to update company branch");
            }
        } catch (Exception e) {
            logger.error("Company branch with id {} does not exist!", id);
        }
        return null;

    }

    public void deleteBranch(Integer id) {
        try {
            companyRepository.deleteById(id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete company branch");
        }
    }


}
