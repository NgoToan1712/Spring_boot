package com.company.opentalk.repository;

import com.company.opentalk.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query("select t from Topic t where t.topicId= ?1")
    Topic findTopicById(Integer id);

    @Query(value = "select t from Topic t")
    List<Topic> getAll();

    @Query(value = "select  t from Topic t where t.time>=?1 and t.time<=?2")
    List<Topic> getTopicByTime(LocalDateTime date1, LocalDateTime date2);
}