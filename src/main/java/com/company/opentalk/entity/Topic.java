package com.company.opentalk.entity;


import com.company.opentalk.dto.TopicDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic")
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Integer topicId;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "meeting_link")
    private String meetingLink;

    @Column(name = "description")
    private String description;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    public Topic(String topicName, LocalDateTime time, String meetingLink,
                 String description, List<Employee> organizers, List<CompanyBranch> companyBranches) {
        this.topicName = topicName;
        this.time = time;
        this.meetingLink = meetingLink;
        this.description = description;
        this.organizers = organizers;
        this.companyBranches = companyBranches;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "topic_organizers",
            joinColumns = @JoinColumn(name = "topic_id"),//khoa ngoại chính là topic_id trỏ tới class hiện tại
            inverseJoinColumns = @JoinColumn(name = "employee_id")//khóa ngoại trỏ tới thuộc tính ở employee
    )
    private List<Employee> organizers = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "topic_participants",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> participants = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "topic_company_branch",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    private List<CompanyBranch> companyBranches = new ArrayList<>();

    public TopicDto toDto() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String date = this.getTime().format(df);
        String lastModifiedDate;
        if (this.lastModifiedDate != null) {
            lastModifiedDate = this.getLastModifiedDate().format(df);
        } else {
            lastModifiedDate = null;
        }
        List<String> branchNames = new ArrayList<>();
        for (CompanyBranch item : this.companyBranches) {
            branchNames.add(item.getBranchName());
        }
        List<Integer> participantId = new ArrayList<>();
        for (Employee e : this.participants) {
            participantId.add(e.getEmployeeId());
        }
        List<Integer> organizerId = new ArrayList<>();
        for (Employee e : this.organizers) {
            organizerId.add(e.getEmployeeId());
        }
        return TopicDto.builder().topicId(this.getTopicId())
                .topicName(this.getTopicName())
                .time(date)
                .meetingLink(this.getMeetingLink())
                .description(this.getDescription())
                .lastModifiedDate(lastModifiedDate)
                .branch(branchNames)
                .organizers(organizerId)
                .participants(participantId)
                .build();
    }


}
