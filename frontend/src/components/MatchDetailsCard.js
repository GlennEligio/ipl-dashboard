import React from "react";
import { Link } from "react-router-dom";

export const MatchDetailsCard = ({ teamName, match }) => {
  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/team/${otherTeam}`;
  return (
    <div>
      <h3>Latest Match</h3>
      <h1>
        vs <Link to={otherTeamRoute}>{otherTeam}</Link>
      </h1>
      <h2>{match.date}</h2>
      <h3>at {match.venue}</h3>
      <h3>
        {match.winner} won by {match.resultMargin} {match.result}
      </h3>
    </div>
  );
};
