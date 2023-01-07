import React, { useEffect, useState } from "react";
import { MatchDetailsCard } from "../components/MatchDetailsCard";
import { MatchSmallCard } from "../components/MatchSmallCard";

export const TeamPage = () => {
  const [team, setTeam] = useState({});

  useEffect(() => {
    fetchMatches();
  }, []);

  const fetchMatches = async () => {
    const response = await fetch(
      "http://localhost:8080/api/v1/teams/Delhi%20Capitals"
    );
    const data = await response.json();
    console.log(data);
    setTeam(data);
  };

  return (
    <div>
      <h1>{team.teamName}</h1>
      {team.matches && team.matches.length > 0 && (
        <>
          <MatchDetailsCard match={team.matches[0]} />
          {team.matches.slice(1).map((m) => (
            <MatchSmallCard match={m} />
          ))}
        </>
      )}
    </div>
  );
};
