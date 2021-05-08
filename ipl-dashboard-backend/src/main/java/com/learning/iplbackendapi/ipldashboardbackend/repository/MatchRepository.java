package com.learning.iplbackendapi.ipldashboardbackend.repository;

import java.util.List;

import com.learning.iplbackendapi.ipldashboardbackend.model.MatchModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchModel, Long> {
    
    List<MatchModel> findByTeam1OrTeam2 (String team1, String team2) throws Exception;
}
