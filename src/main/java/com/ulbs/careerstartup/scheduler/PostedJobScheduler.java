package com.ulbs.careerstartup.scheduler;

import com.lowagie.text.DocumentException;
import com.ulbs.careerstartup.constant.JobStatus;
import com.ulbs.careerstartup.entity.PostedJob;
import com.ulbs.careerstartup.repository.PostedJobRepository;
import com.ulbs.careerstartup.util.RecruiterNotificator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class PostedJobScheduler {

    private PostedJobRepository postedJobRepository;
    private RecruiterNotificator recruiterNotificator;

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Bucharest")
    public void checkJobDeadlines() {
        LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.of("Europe/Bucharest"));
        Timestamp now = Timestamp.valueOf(localDateTimeNow);

        List<PostedJob> overdueJobs = postedJobRepository.findByOpenUntilBefore(now).stream().filter(job -> job.getStatus().equals(JobStatus.ACTIVE)).toList();

        if (!overdueJobs.isEmpty()) {
            overdueJobs.forEach(job -> {
                job.setStatus(JobStatus.INACTIVE);
                postedJobRepository.save(job);
            });

            log.info("Found " + overdueJobs.size() + " jobs with past deadlines:");

            overdueJobs.forEach(job -> {
                try {
                    recruiterNotificator.notify(job);
                } catch (DocumentException | IOException e) {
                    PostedJobScheduler.log.error(String.format("Error while generating CVs for job candidates an notifying the recruiter for job= %s", job), e);
                }
            });

        } else {
            log.info("No jobs with past deadlines found.");
        }
    }
}
