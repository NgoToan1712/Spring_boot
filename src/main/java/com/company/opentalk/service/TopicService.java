package com.company.opentalk.service;

import com.company.opentalk.dto.TopicDto;
import com.company.opentalk.entity.CompanyBranch;
import com.company.opentalk.entity.Employee;
import com.company.opentalk.entity.Topic;
import com.company.opentalk.repository.CompanyBranchRepository;
import com.company.opentalk.repository.EmployeeRepository;
import com.company.opentalk.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyBranchRepository branchRepository;

    public List<TopicDto> getTopicAll() {
        return topicRepository.getAll().stream().map(item -> item.toDto()).collect(Collectors.toList());
    }

    public TopicDto getTopicById(Integer id) {
        return topicRepository.findTopicById(id).toDto();
    }

    public void addTopic(TopicDto topicDto) {
        LocalDateTime dateTime = LocalDateTime.parse(topicDto.getTime(),
                DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));
        List<Employee> organizers = new ArrayList<>();
        for (Integer id : topicDto.getOrganizers()) {
            Employee employee = employeeRepository.findById(id).get();
            organizers.add(employee);
        }
        List<CompanyBranch> branches = new ArrayList<>();
        for (String branchName : topicDto.getBranch()) {
            CompanyBranch branch = branchRepository.findByranchName(branchName);
            branches.add(branch);
        }
        Topic topic = new Topic(topicDto.getTopicName(), dateTime,
                topicDto.getMeetingLink(), topicDto.getDescription(), organizers, branches);
        topicRepository.save(topic);
    }

    public void updateTopic(Integer topicId, TopicDto topicDto) {
        Topic topic = topicRepository.findTopicById(topicId);
        List<Employee> organizers = new ArrayList<>();
        for (Integer id : topicDto.getOrganizers()) {
            Employee employee = employeeRepository.findById(id).get();
            organizers.add(employee);
        }
        List<CompanyBranch> branches = new ArrayList<>();
        for (String branchName : topicDto.getBranch()) {
            CompanyBranch branch = branchRepository.findByranchName(branchName);
            branches.add(branch);
        }
        LocalDateTime dateTime = LocalDateTime.parse(topicDto.getTime(),
                DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));
        topic.setTopicName(topicDto.getTopicName());
        topic.setLastModifiedDate(dateTime);
        topic.setMeetingLink(topicDto.getMeetingLink());
        topic.setDescription(topicDto.getDescription());
        topic.setOrganizers(organizers);
        topic.setCompanyBranches(branches);
        topicRepository.save(topic);
    }

    public void deleteTopic(Integer id) {
        topicRepository.deleteById(id);
    }
}
