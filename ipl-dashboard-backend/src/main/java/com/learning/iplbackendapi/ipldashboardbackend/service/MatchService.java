package com.learning.iplbackendapi.ipldashboardbackend.service;

import java.util.ArrayList;
import java.util.List;

import com.learning.iplbackendapi.ipldashboardbackend.model.MatchModel;
import com.learning.iplbackendapi.ipldashboardbackend.repository.MatchRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    private static final Logger log = LoggerFactory.getLogger(MatchService.class);

    public List<MatchModel> getAllMatchesByTeamName (String teamName) {
        List<MatchModel> matches = new ArrayList<>();
        try {
            matches = matchRepository.findByTeam1OrTeam2(teamName, teamName);
        } catch (Exception e) {
            e.getMessage();
        }
        log.info("List size: " + matches.size());
        return matches;
    }
    
}
