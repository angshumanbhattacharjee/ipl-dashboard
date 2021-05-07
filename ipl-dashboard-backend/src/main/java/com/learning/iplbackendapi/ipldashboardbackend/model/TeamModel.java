package com.learning.iplbackendapi.ipldashboardbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "team_model")
public class TeamModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long teamId;
    private String teamName;
    private long totalMatchesPlayed;
    private long totalMatchesWon;

    public TeamModel(String teamName, long totalMatchesPlayed) {
        this.teamName = teamName;
        this.totalMatchesPlayed = totalMatchesPlayed;
    }

    public TeamModel() {
    }



    public long getTeamId() {
        return teamId;
    }
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }
    public void setTotalMatchesPlayed(long totalMatchesPlayed) {
        this.totalMatchesPlayed = totalMatchesPlayed;
    }
    public long getTotalMatchesWon() {
        return totalMatchesWon;
    }
    public void setTotalMatchesWon(long totalMatchesWon) {
        this.totalMatchesWon = totalMatchesWon;
    }

    
}
