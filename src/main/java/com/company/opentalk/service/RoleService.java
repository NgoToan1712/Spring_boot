package com.company.opentalk.service;

import com.company.opentalk.dto.RoleDto;
import com.company.opentalk.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream()
                .map(role -> mapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    public RoleDto getById(Integer id) {
        return mapper.map(roleRepository.findById(id).get(), RoleDto.class);
    }

    public RoleDto createRole(RoleDto roleDto) {
        return null;
    }
}
