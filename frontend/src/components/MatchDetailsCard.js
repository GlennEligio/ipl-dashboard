import React from "react";

export const MatchDetailsCard = (props) => {
  return (
    <div>
      <h3>Latest Match</h3>
      <h4>Match Details</h4>
      <h4>
        {props.match.team1} vs {props.match.team2}
      </h4>
    </div>
  );
};
