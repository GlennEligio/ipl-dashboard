package com.glenneligio.ipldashboard.data;

import com.glenneligio.ipldashboard.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamData = new HashMap<>();

            /*
            * Get total instances of each team playing first and second inning (represents total matches)
            * Get instances of each team in the "winner" column, represents totalWins
            * Persist the teams stored in the Map of String+Team
            * */
            em.createQuery("SELECT m.team1, count(*) FROM Match m GROUP BY m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(rs -> new Team((String) rs[0], (long) rs[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            em.createQuery("SELECT m.team2, count(*) FROM Match m GROUP BY m.team2", Object[].class)
                    .getResultList()
                    .stream()
                    .peek(rs -> {
                        String teamName = (String) rs[0];
                        if(teamData.get(teamName) == null) {
                            teamData.put(teamName,new Team(teamName, (long) rs[1]));
                        }
                    })
                    .forEach(rs -> {
                        String teamName = (String) rs[0];
                        Team team = teamData.get(teamName);
                        team.setTotalMatches(team.getTotalMatches() + (long) rs[1]);
                    });

            em.createQuery("SELECT m.winner, count(*) FROM Match m GROUP BY m.winner", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(rs -> {
                        String teamName = (String) rs[0];
                        Team team = teamData.get(teamName);
                        if(team != null) {
                            team.setTotalWins((long) rs[1]);
                        }
                    });

            teamData.values()
                    .stream().peek(System.out::println)
                    .forEach(em::persist);
        }
    }
}