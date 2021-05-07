package com.learning.iplbackendapi.ipldashboardbackend.repository;

import java.util.Optional;

import com.learning.iplbackendapi.ipldashboardbackend.model.TeamModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, Long> {

    Optional<TeamModel> findByTeamName(String teamName) throws Exception;
    
}
