package com.learning.iplbackendapi.ipldashboardbackend.Listener;

import javax.persistence.EntityManager;

import com.learning.iplbackendapi.ipldashboardbackend.model.TeamModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Map<String, TeamModel> teamData = new HashMap<>();

      entityManager.createQuery("select m.team1, count(*) from MatchModel m group by m.team1", Object[].class)
        .getResultList()
        .stream()
        .map(result -> new TeamModel((String) result[0], (long) result[1]))
        .forEach(teamModel -> teamData.put(teamModel.getTeamName(), teamModel));

      entityManager.createQuery("select m.team2, count(*) from MatchModel m group by m.team2", Object[].class)
        .getResultList()
        .forEach(result -> {
          TeamModel teamModel = teamData.get((String) result[0]);
          if (teamModel != null) teamModel.setTotalMatchesPlayed(teamModel.getTotalMatchesPlayed()+ (long) result[1]);
        });

      entityManager.createQuery("select m.matchWinner, count(*) from MatchModel m group by m.matchWinner", Object[].class)
        .getResultList()
        .forEach(result -> {
          TeamModel teamModel = teamData.get((String) result[0]);
          if (teamModel != null) teamModel.setTotalMatchesWon((long) result[1]);
        });

      teamData.values()
        .forEach(teamModel -> entityManager.persist(teamModel));

      entityManager.createQuery("select t.teamId, t.teamName, t.totalMatchesPlayed, t.totalMatchesWon from TeamModel t", Object[].class)
        .getResultList()
        .forEach(result -> System.out.println("TeamId: " + (long) result[0] + "  TeamName: " + (String) result[1] + " TotalMatchedPlayed: " + (long) result[2] + " TotalMatchesWon: " + (long) result[3]));
    }
  }
}
