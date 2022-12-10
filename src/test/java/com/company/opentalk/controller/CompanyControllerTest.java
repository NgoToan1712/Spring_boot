package com.company.opentalk.controller;

import com.company.opentalk.dto.CompanyBranchDto;
import com.company.opentalk.entity.CompanyBranch;
import com.company.opentalk.service.CompanyBranchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
//Cung cấp lớp controller cho @WebMvcTest
@WebMvcTest(CompanyBranchController.class)
@ContextConfiguration
public class CompanyControllerTest {

    // đối tượng MockMvc do spring cung cấp. có tác dụng giả lập request, thay thế việc khởi động server
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyBranchService branchService;

    List<CompanyBranch> companyBranches = new ArrayList<>();

    CompanyBranch companyBranch;

    public static final Logger logger = LoggerFactory.getLogger(CompanyControllerTest.class);

    @BeforeEach
    public void init() {
        logger.info("startup");
        companyBranches.add(new CompanyBranch(1, "HN1", "Ha Noi"));
        companyBranches.add(new CompanyBranch(2, "DN", "Da Nang"));
        companyBranches.add(new CompanyBranch(3, "SG", "Sai Gon"));
        companyBranches.add(new CompanyBranch(4, "VINH", "Vinh"));
        companyBranches.add(new CompanyBranch(5, "QN", "Quang Ninh"));
        companyBranches.add(new CompanyBranch(6, "HUE", "Hue"));
        companyBranches.add(new CompanyBranch(7, "ND", "Nam Dinh"));

        companyBranch = new CompanyBranch(1, "HN", "Ha Noi");
    }

    @AfterEach
    public void teardown() {
        logger.info("teardown");
        companyBranches.clear();
    }

   // @WithMockUser(username = "admin",authorities = {"ROLE_ADMIN"})
    @Test
    public void testFindAll() throws Exception {

        given(branchService.getBranchAll()).willReturn(companyBranches.stream()
                .map(CompanyBranch::toDto).collect(Collectors.toList()));
        mvc.perform(get("/company-branch").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk()) // Mong muốn Server trả về status 200
                .andExpect(jsonPath("$", hasSize(7))) // Hi vọng server trả về List độ dài 10
                .andExpect(jsonPath("$[0].branchId", is(1))) // Hi vọng phần tử trả về đầu tiên có id = 0
                .andExpect(jsonPath("$[0].branchName", is("HN1"))) // Hi vọng phần tử trả về đầu tiên có title = "title-0"
                .andExpect(jsonPath("$[0].location", is("Ha Noi")));
    }

    @Test
    public void testFindById() throws Exception {
        given(branchService.getBranchById(1)).willReturn(companyBranches.get(1).toDto());
        mvc.perform(get("/company-branch/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateCompany() throws Exception {
        given(branchService.saveBranch(any(CompanyBranchDto.class))).willReturn(companyBranch.toDto());
        mvc.perform(post("/company-branch")
                        .content(asJsonString(companyBranch.toDto()).toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));
    }

}



