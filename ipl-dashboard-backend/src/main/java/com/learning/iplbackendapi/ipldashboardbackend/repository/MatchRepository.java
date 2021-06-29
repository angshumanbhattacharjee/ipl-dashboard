package com.learning.iplbackendapi.ipldashboardbackend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.learning.iplbackendapi.ipldashboardbackend.model.MatchModel;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchModel, Long> {
    
    public List<MatchModel> findByTeam1OrTeam2OrderByDateDesc (String team1, String team2, Pageable pageable) throws Exception;

    @Query("select m from MatchModel m where (m.team1= :teamName or m.team2= :teamName) and m.date between :startDate and :endDate order by date desc")
    public Optional<List<MatchModel>> getMatchesByTeamAndDate(
        @Param("teamName") String teamName, 
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate
    ) throws Exception;

    default public List<MatchModel> getlatestMatchesByTeamName (String teamName, int matchCount) {
        try {
            return findByTeam1OrTeam2OrderByDateDesc (teamName, teamName, PageRequest.of(0, matchCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
