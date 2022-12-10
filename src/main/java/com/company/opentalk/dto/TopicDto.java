package com.company.opentalk.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class TopicDto {


    private Integer topicId;

    @NotNull(message = "Topic name cannot be blank")
    private String topicName;

    private String time;

    private String meetingLink;

    private String description;

    private String lastModifiedDate;

    private List<Integer> organizers;

    private List<Integer> participants;

    private List<String> branch;



    public TopicDto(String topicName, String time, String meetingLink,
                    String description, List<Integer> organizers, List<String> branch) {
        this.topicName = topicName;
        this.time = time;
        this.meetingLink = meetingLink;
        this.description = description;
        this.organizers = organizers;
        this.branch = branch;
    }


}
