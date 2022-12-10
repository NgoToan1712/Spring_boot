package com.company.opentalk.service;

import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.entity.Employee;
import com.company.opentalk.entity.Topic;
import com.company.opentalk.repository.EmployeeRepository;
import com.company.opentalk.repository.TopicRepository;
import com.company.opentalk.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class EmployeeTopicService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private TopicRepository topicRepository;

    public List<EmployeeDto> getOrganizers(Integer topicId) {
        return topicRepository.findById(topicId).get().getOrganizers()
                .stream()
                .map(Employee::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> getParticipants(Integer topicId) {
        return topicRepository.findById(topicId).get().getParticipants()
                .stream()
                .map(Employee::toDto)
                .collect(Collectors.toList());
    }

    public void addOrganizers(String authHeader, Integer topicId) {
        String email = jwtTokenProvider.getUserIdFromJWT(authHeader);
        Employee employee = employeeRepository.findByEmail(email);
        Topic topic = topicRepository.findTopicById(topicId);
        List<Employee> organizers = topic.getOrganizers();
        organizers.add(employee);
        topic.setOrganizers(organizers);
        topicRepository.save(topic);
    }

    public void addParticipant(String authHeader, Integer topicId) {
        String email = jwtTokenProvider.getUserIdFromJWT(authHeader);
        Employee employee = employeeRepository.findByEmail(email);
        Topic topic = topicRepository.findTopicById(topicId);
        List<Employee> participants = topic.getParticipants();
        participants.add(employee);
        topic.setParticipants(participants);
        topicRepository.save(topic);
    }

    public void deleteOrganizers(String authHeader, Integer topicId) {
        String email = jwtTokenProvider.getUserIdFromJWT(authHeader);
        Employee employee = employeeRepository.findByEmail(email);
        Topic topic = topicRepository.findTopicById(topicId);
        List<Employee> organizers = topic.getOrganizers();
        organizers.remove(employee);
        topic.setOrganizers(organizers);
        topicRepository.save(topic);
    }

    public void deleteParticipant(String authHeader, Integer topicId) {
        String email = jwtTokenProvider.getUserIdFromJWT(authHeader);
        Employee employee = employeeRepository.findByEmail(email);
        Topic topic = topicRepository.findTopicById(topicId);
        List<Employee> participants = topic.getParticipants();
        participants.remove(employee);
        topic.setParticipants(participants);
        topicRepository.save(topic);
    }

}
