package com.glenneligio.ipldashboard.repo;

import com.glenneligio.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("SELECT m FROM Match m WHERE (m.team1=:teamName OR m.team2=:teamName) " +
            "AND (m.date BETWEEN :date1 AND :date2) " +
            "ORDER BY date desc")
    List<Match> getMatchesByTeamNameBetweenDate(
            @Param("teamName") String teamName,
            @Param("date1") LocalDate date1,
            @Param("date2") LocalDate date2);

//    List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
//            String teamName1, LocalDate date1, LocalDate date2,
//            String teamName2, LocalDate date3, LocalDate date4
//    );

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
