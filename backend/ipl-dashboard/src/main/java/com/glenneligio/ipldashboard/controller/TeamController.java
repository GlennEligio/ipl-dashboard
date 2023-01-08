package com.glenneligio.ipldashboard.controller;

import com.glenneligio.ipldashboard.model.Match;
import com.glenneligio.ipldashboard.model.Team;
import com.glenneligio.ipldashboard.repo.MatchRepository;
import com.glenneligio.ipldashboard.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private TeamRepository repository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository repository, MatchRepository matchRepository) {
        this.repository = repository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/{teamName}")
    public ResponseEntity<Team> getTeam(@PathVariable String teamName) {
        Optional<Team> team = repository.findByTeamName(teamName);
        List<Match> matches = matchRepository.findLatestMatchesByTeam(teamName, 4);
        return team.map(team1 -> {
            team1.setMatches(matches);
            return ResponseEntity.ok(team1);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{teamName}/matches")
    public ResponseEntity<List<Match>> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return ResponseEntity.ok(matchRepository.getMatchesByTeamNameBetweenDate(
                teamName,
                startDate,
                endDate
        ));
    }
}
