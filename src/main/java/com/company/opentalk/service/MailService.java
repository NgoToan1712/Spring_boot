package com.company.opentalk.service;

import com.company.opentalk.dto.AccountDto;
import com.company.opentalk.entity.Employee;
import com.company.opentalk.entity.Topic;
import com.company.opentalk.repository.EmployeeRepository;
import com.company.opentalk.repository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MailService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TopicRepository topicRepository;

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Scheduled(cron = "0 0 9 * * 4")
    public void sendEmail() throws MessagingException {
        LocalDateTime localDate1 = LocalDateTime.now();
        LocalDateTime localDate2 = LocalDateTime.now().plusDays(7);

        List<Topic> topics = topicRepository.getTopicByTime(localDate1, localDate2);
        List<String> emails = employeeRepository.getEmail();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        for (String email : emails) {
            for (Topic topic : topics) {
                LocalDateTime dateTime = topic.getTime();
                String date = dateTime.format(df);
                String host = "";
                for (Employee employee : topic.getOrganizers()) {
                    String str = employee.getLastName() + " " + employee.getLastName() + ", ";
                    host = host + str;
                }
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                String htmlMsg = "Subject: " + topic.getTopicName() + "<br>Content: " + topic.getDescription()
                        + "<br>Time: " + date + "<br>Meeting link: " + topic.getMeetingLink()
                        + "<br>Organizers: " + host;
                message.setContent(htmlMsg, "text/html");
                helper.setTo(email);
                helper.setSubject("Open Talk");
                mailSender.send(message);
            }
        }
        logger.info("--------Send mail successful!");

    }

    //fixedRate lặp
    //fixedDelay done
    //initialDelay,fixedRate; sau khi triển khai method cho phép triển khai sau khoảng tg initialDelay

    @Async
    public void sendEmailCreateEmployee(AccountDto accountDto) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String html = "<br>First Name: " + accountDto.getFirstName()
                + "<br>Last Name: " + accountDto.getLastName()
                + "<br>Date Of Birth: " + accountDto.getDateOfBirth()
                + "<br>Phone: " + accountDto.getPhone()
                + "<br>Email: " + accountDto.getEmail()
                + "<br>Branch: " + accountDto.getBranch()
                + "<br>Password: " + accountDto.getPassword();
        message.setContent(html, "text/html");
        helper.setTo(accountDto.getEmail());
        helper.setSubject("Account Information");
        mailSender.send(message);

    }
}
