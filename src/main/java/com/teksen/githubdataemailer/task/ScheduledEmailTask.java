package com.teksen.githubdataemailer.task;

import com.teksen.githubdataemailer.repository.GitHubDataRepository;
import com.teksen.githubdataemailer.service.GitHubAPIService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledEmailTask {

    private final GitHubAPIService gitHubAPIService;
    private final GitHubDataRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(ScheduledEmailTask.class);

    private long fixedDelay;

    private long initialDelay;

    @Autowired
    public ScheduledEmailTask(GitHubAPIService gitHubAPIService, GitHubDataRepository repository,
                              @Value("${scheduled.task.fixed-delay}") long fixedDelay,
                              @Value("${scheduled.task.initial-delay}") long initialDelay) {
        this.gitHubAPIService = gitHubAPIService;
        this.repository = repository;
        this.fixedDelay = fixedDelay;
        this.initialDelay = initialDelay;
    }

    @Value("${scheduled.task.time-zone}")
    private String timeZone;


    public ScheduledEmailTask(GitHubAPIService gitHubAPIService, GitHubDataRepository repository) {
        this.gitHubAPIService = gitHubAPIService;
        this.repository = repository;
    }

    @Scheduled(initialDelayString = "${scheduled.task.initial-delay}", fixedDelayString = "${scheduled.task.fixed-delay}")
    public void sendScheduledEmail() throws MessagingException {
        gitHubAPIService.sendGitHubDataEmailsViaHtml(repository.findAll());
        LOGGER.info("Emails sent successfully.");

        long currentTimeMillis = System.currentTimeMillis();
        long nextExecutionTime = currentTimeMillis + fixedDelay;

        Date nextExecutionDate = new Date(nextExecutionTime);

        TimeZone selectedTimeZone = TimeZone.getTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        simpleDateFormat.setTimeZone(selectedTimeZone);

        String nextExecutionTimeFormatted = simpleDateFormat.format(nextExecutionDate);
        LOGGER.info("Next email will be sent at: {}", nextExecutionTimeFormatted);
    }






}
