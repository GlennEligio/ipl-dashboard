import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { MatchDetailsCard } from "../components/MatchDetailsCard";
import { MatchSmallCard } from "../components/MatchSmallCard";
import { PieChart } from "react-minimal-pie-chart";
import "./TeamPage.scss";

export const TeamPage = () => {
  const [team, setTeam] = useState({ matches: [] });
  const { teamName } = useParams();
  const endYear = process.env.REACT_APP_DATA_END_YEAR;

  useEffect(() => {
    fetchMatches();
  }, [teamName]);

  const fetchMatches = async () => {
    const uri = encodeURI(
      `${process.env.REACT_APP_API_ROOT_URL}/api/v1/teams/${teamName}`
    );
    const response = await fetch(uri);
    const data = await response.json();
    setTeam(data);
  };

  if (!team || !team.matches) {
    return <h1>Team not found</h1>;
  }

  return (
    <div className="TeamPage">
      <div className="team-name-section">
        <h1 className="team-name">{team.teamName}</h1>
      </div>
      <div className="win-loss-section">
        Win/Losses
        <PieChart
          data={[
            {
              title: "Loss",
              value: team.totalMatches - team.totalWins,
              color: "#a34d5d",
            },
            { title: "Win", value: team.totalWins, color: "#4da375" },
          ]}
        />
        ;
      </div>
      {team.matches && team.matches.length > 0 && (
        <>
          <div className="match-detail-section">
            <h3>Latest Match</h3>
            <MatchDetailsCard
              match={team.matches[0]}
              teamName={team.teamName}
            />
          </div>
          {team.matches.slice(1).map((m) => (
            <MatchSmallCard match={m} key={m.id} teamName={team.teamName} />
          ))}
        </>
      )}
      <div className="more-link">
        <Link to={`/team/${team.teamName}/matches/${endYear}`}>{"More >"}</Link>
      </div>
    </div>
  );
};
