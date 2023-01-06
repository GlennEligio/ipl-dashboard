package com.glenneligio.ipldashboard.data;

import com.glenneligio.ipldashboard.model.Match;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

@Slf4j
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(final MatchInput inputMatch) throws Exception {
        log.info("Processing input data {}", inputMatch);
        Match match = new Match();
        match.setId(Long.parseLong(inputMatch.getId()));
        match.setCity(inputMatch.getCity());
        match.setDate(LocalDate.parse(inputMatch.getDate()));
        match.setPlayerOfMatch(inputMatch.getPlayer_of_match());
        match.setVenue(inputMatch.getVenue());
        match.setTeam1(inputMatch.getTeam1());
        match.setTeam2(inputMatch.getTeam2());

        // Sets the team1 to be the firstInningsTeam and team2 to be secondInningsTeam
        String firstInningsTeam, secondInningsTeam;
        if("bat".equals(inputMatch.getToss_decision())) {
            firstInningsTeam = inputMatch.getToss_winner();
            secondInningsTeam = inputMatch.getToss_winner().equals(inputMatch.getTeam1()) ?
                    inputMatch.getTeam2() :
                    inputMatch.getTeam1();
        } else {
            secondInningsTeam = inputMatch.getToss_winner();
            firstInningsTeam = inputMatch.getToss_winner().equals(inputMatch.getTeam1()) ?
                    inputMatch.getTeam2() :
                    inputMatch.getTeam1();
        }
        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);

        match.setTossWinner(inputMatch.getToss_winner());
        match.setTossDecision(inputMatch.getToss_decision());
        match.setWinner(inputMatch.getWinner());
        match.setResult(inputMatch.getResult());
        match.setResultMargin(inputMatch.getResult_margin());
        match.setUmpire1(inputMatch.getUmpire1());
        match.setUmpire2(inputMatch.getUmpire2());

        return match;
    }
}