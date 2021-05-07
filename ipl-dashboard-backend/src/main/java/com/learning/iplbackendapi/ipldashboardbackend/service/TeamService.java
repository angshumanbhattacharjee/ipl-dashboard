package com.learning.iplbackendapi.ipldashboardbackend.service;

import java.util.Optional;

import com.learning.iplbackendapi.ipldashboardbackend.model.TeamModel;
import com.learning.iplbackendapi.ipldashboardbackend.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public TeamModel getTeamModel(String teamName) throws Exception {
        Optional<TeamModel> teamModel = teamRepository.findByTeamName(teamName);
        if (teamModel.isPresent()) {
            return teamModel.get();
        }
        return null;
    }
    
}
