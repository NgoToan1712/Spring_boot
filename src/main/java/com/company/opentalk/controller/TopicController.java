package com.company.opentalk.controller;

import com.company.opentalk.dto.TopicDto;
import com.company.opentalk.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<TopicDto> getTopicAll() {
        return topicService.getTopicAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getById(@PathVariable Integer id) {
        try {
            TopicDto topicDto = topicService.getTopicById(id);
            return new ResponseEntity<TopicDto>(topicDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<TopicDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> addTopic(@Valid @RequestBody TopicDto topicDto) {
        topicService.addTopic(topicDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateTopic(@RequestParam("topicId") Integer topicId,
                                         @Valid @RequestBody TopicDto topicDto) {
        topicService.updateTopic(topicId, topicDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable Integer id) {
        topicService.deleteTopic(id);
    }
}
