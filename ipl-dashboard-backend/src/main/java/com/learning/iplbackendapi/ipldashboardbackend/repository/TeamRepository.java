package com.learning.iplbackendapi.ipldashboardbackend.repository;

import java.util.Optional;
import com.learning.iplbackendapi.ipldashboardbackend.model.TeamModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<TeamModel, Long> {

    Optional<TeamModel> findByTeamName(String teamName) throws Exception;
    
}
