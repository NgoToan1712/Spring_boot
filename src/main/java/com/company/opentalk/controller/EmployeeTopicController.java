package com.company.opentalk.controller;

import com.company.opentalk.dto.EmployeeDto;
import com.company.opentalk.entity.Employee;
import com.company.opentalk.service.EmployeeTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-topic")
public class EmployeeTopicController {
    @Autowired
    private EmployeeTopicService employeeTopicService;

    @GetMapping("/organizers")
    public List<EmployeeDto> getOrganizers(@RequestParam("topicId") Integer topicId) {
        return employeeTopicService.getOrganizers(topicId);
    }

    @GetMapping("/participants")
    public List<EmployeeDto> getParticipant(@RequestParam("topicId") Integer topicId) {
        return employeeTopicService.getParticipants(topicId);
    }

    @PostMapping("/add-organizer")
    public ResponseEntity<?> addOrganizer(@RequestHeader("Authorization") String authHeader,
                                          @RequestParam("topicId") Integer topicId) {
        employeeTopicService.addOrganizers(authHeader, topicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-participant")
    public ResponseEntity<?> addParticipant(@RequestHeader("Authorization") String authHeader,
                                            @RequestParam("topicId") Integer topicId) {
        employeeTopicService.addParticipant(authHeader, topicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-organizer")
    public ResponseEntity<?> deleteOrganizer(@RequestHeader("Authorization") String authHeader,
                                             @RequestParam("topicId") Integer topicId) {
        employeeTopicService.deleteOrganizers(authHeader, topicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-participant")
    public ResponseEntity<?> deleteParticipant(@RequestHeader("Authorization") String authHeader,
                                               @RequestParam("topicId") Integer topicId) {
        employeeTopicService.deleteParticipant(authHeader, topicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
