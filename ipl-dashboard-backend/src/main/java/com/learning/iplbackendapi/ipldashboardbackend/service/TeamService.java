package com.learning.iplbackendapi.ipldashboardbackend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.learning.iplbackendapi.ipldashboardbackend.model.MatchModel;
import com.learning.iplbackendapi.ipldashboardbackend.model.TeamModel;
import com.learning.iplbackendapi.ipldashboardbackend.repository.MatchRepository;
import com.learning.iplbackendapi.ipldashboardbackend.repository.TeamRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;
    

    @Autowired
    MatchRepository matchRepository;

    private static final Logger log = LoggerFactory.getLogger(TeamService.class);

    public TeamModel getTeamModel(String teamName) throws Exception {
        Optional<TeamModel> teamModel = teamRepository.findByTeamName(teamName);
        if (teamModel.isPresent()) {
            List<MatchModel> matches = new ArrayList<>();
            matches = matchRepository.getlatestMatchesByTeamName(teamName, 5);
            teamModel.get().setLatestMatches(matches);
            return teamModel.get();
        }
        return teamModel.get();
    }

    public List<MatchModel> getMatchesByTeamAndYear(String teamName, int year) throws Exception {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);
        Optional<List<MatchModel>> matches = matchRepository.getMatchesByTeamAndDate(teamName, startDate, endDate);
        if (matches.isPresent()) {
            return matches.get();
        }
        return null;
    }
    
}
