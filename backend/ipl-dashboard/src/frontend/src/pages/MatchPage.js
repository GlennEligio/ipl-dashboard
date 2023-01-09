import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailsCard } from "../components/MatchDetailsCard";
import { YearSelector } from "../components/YearSelector";

import "./MatchPage.scss";

export const MatchPage = () => {
  const { year, teamName } = useParams();
  const [matches, setMatches] = useState([]);

  useEffect(() => {
    getMatchesOfYearByTeam();
  }, [year, teamName]);

  const getMatchesOfYearByTeam = async () => {
    const params = new URLSearchParams({ year: year }).toString();
    const uri = encodeURI(
      `${process.env.REACT_APP_API_ROOT_URL}/api/v1/teams/${teamName}/matches?${params}`
    );
    const response = await fetch(uri);
    const data = await response.json();
    setMatches(data);
  };

  return (
    <div className="MatchPage">
      <div className="year-selector">
        <h3>Select Year</h3>
        <YearSelector teamName={teamName} />
      </div>
      <div>
        <h1 className="page-heading">
          {teamName} matches in {year}
        </h1>
        {matches.map((m) => (
          <MatchDetailsCard key={m.id} match={m} teamName={teamName} />
        ))}
      </div>
    </div>
  );
};
