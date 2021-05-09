package com.learning.iplbackendapi.ipldashboardbackend.repository;

import java.util.List;

import com.learning.iplbackendapi.ipldashboardbackend.model.MatchModel;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchModel, Long> {
    
    public List<MatchModel> findByTeam1OrTeam2OrderByDateDesc (String team1, String team2, Pageable pageable) throws Exception;

    default public List<MatchModel> getlatestMatchesByTeamName (String teamName, int matchCount) {
        try {
            return findByTeam1OrTeam2OrderByDateDesc (teamName, teamName, PageRequest.of(0, matchCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
