import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailsCard } from "../components/MatchDetailsCard";
import { MatchSmallCard } from "../components/MatchSmallCard";

export const TeamPage = () => {
  const [team, setTeam] = useState({});
  const { teamName } = useParams();

  useEffect(() => {
    fetchMatches();
  }, [teamName]);

  const fetchMatches = async () => {
    const uri = decodeURI(`http://localhost:8080/api/v1/teams/${teamName}`);
    const response = await fetch(uri);
    const data = await response.json();
    console.log(data);
    setTeam(data);
  };

  if (!team || !team.matches) {
    return <h1>Team not found</h1>;
  }

  return (
    <div>
      <h1>{team.teamName}</h1>
      {team.matches && team.matches.length > 0 && (
        <>
          <MatchDetailsCard match={team.matches[0]} teamName={team.teamName} />
          {team.matches.slice(1).map((m) => (
            <MatchSmallCard match={m} key={m.id} teamName={team.teamName} />
          ))}
        </>
      )}
    </div>
  );
};
