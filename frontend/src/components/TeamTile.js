import React from "react";
import "./TeamTile.scss";
import { Link } from "react-router-dom";

export const TeamTile = ({ teamName }) => {
  return (
    <div className="TeamTile">
      <h1>
        <Link to={`/team/${teamName}`}>{teamName}</Link>
      </h1>
    </div>
  );
};
