import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailsCard } from "../components/MatchDetailsCard";

export const MatchPage = () => {
  const { year, teamName } = useParams();
  const [matches, setMatches] = useState([]);

  useEffect(() => {
    getMatchesOfYearByTeam();
  }, [year, teamName]);

  const getMatchesOfYearByTeam = async () => {
    const params = new URLSearchParams({ year: year }).toString();
    const uri = decodeURI(
      `http://localhost:8080/api/v1/teams/${teamName}/matches?${params}`
    );
    const response = await fetch(uri);
    const data = await response.json();
    console.log(data);
    setMatches(data);
  };

  return (
    <div className="MatchPage">
      <h1>Match Page</h1>
      {matches.map((m) => (
        <MatchDetailsCard key={m.id} match={m} teamName={teamName} />
      ))}
    </div>
  );
};
