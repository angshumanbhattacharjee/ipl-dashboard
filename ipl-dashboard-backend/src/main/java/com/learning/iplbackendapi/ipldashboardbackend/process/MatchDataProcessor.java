package com.learning.iplbackendapi.ipldashboardbackend.process;

import java.time.LocalDate;

import com.learning.iplbackendapi.ipldashboardbackend.data.MatchInput;
import com.learning.iplbackendapi.ipldashboardbackend.model.MatchModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, MatchModel> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public MatchModel process(MatchInput matchInput) throws Exception {

    MatchModel matchModel = new MatchModel();

    try {
      if (matchInput != null) {
        matchModel = prepareMatchModelObject (matchInput);
      }
    } catch (Exception e) {
       throw new Exception("Error in MatchInput model: ", e);
    }
    
    //log.info("Converting (" + person + ") into (" + transformedPerson + ")");

    return matchModel;
  }

  private static MatchModel prepareMatchModelObject (MatchInput matchInput) {
    MatchModel matchModel = new MatchModel();

    String firstInningsTeam = null, secondInningsTeam = null;

    if (matchInput.getToss_decision().equalsIgnoreCase("bat")) {
      firstInningsTeam = matchInput.getToss_winner();
      secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
    }
    else {
      secondInningsTeam = matchInput.getToss_winner();
      firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
    }

    matchModel.setId(Long.parseLong(matchInput.getId()));
    matchModel.setCity(matchInput.getCity());
    matchModel.setDate(LocalDate.parse(matchInput.getDate()));
    matchModel.setPlayerOfMatch(matchInput.getPlayer_of_match());
    matchModel.setVenue(matchInput.getVenue());
    matchModel.setNeutralVenue(Boolean.parseBoolean(matchInput.getNeutral_venue()));
    matchModel.setTeam1(firstInningsTeam);
    matchModel.setTeam2(secondInningsTeam);
    matchModel.setTossWinnerTeam(matchInput.getToss_winner());
    matchModel.setTossDecision(matchInput.getToss_decision());
    matchModel.setMatchWinner(matchInput.getWinner());
    matchModel.setResult(matchInput.getResult());
    matchModel.setResultMargin(matchInput.getResult_margin());
    matchModel.setEliminator(matchInput.getEliminator());
    matchModel.setMethod(matchInput.getMethod());
    matchModel.setUmpire1(matchInput.getUmpire1());
    matchModel.setUmpire2(matchInput.getUmpire2());

    return matchModel;
  }

}
