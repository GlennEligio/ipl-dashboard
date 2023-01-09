import React, { useEffect, useState } from "react";
import { TeamTile } from "../components/TeamTile";
import "./HomePage.scss";

export const HomePage = () => {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    fetchTeams();
  }, []);

  const fetchTeams = async () => {
    const uri = process.env.REACT_APP_API_ROOT_URL;
    console.log(uri);
    const response = await fetch(`${uri}/api/v1/teams`);
    const data = await response.json();
    setTeams(data);
  };

  return (
    <div className="HomePage">
      <div className="header-section">
        <h1 className="app-name">Java Brains IPL Dashboard</h1>
      </div>
      <div className="team-grid">
        {teams.map((team) => (
          <TeamTile teamName={team.teamName} key={team.teamName} />
        ))}
      </div>
    </div>
  );
};
