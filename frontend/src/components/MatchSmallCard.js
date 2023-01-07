import React from "react";

export const MatchSmallCard = (props) => {
  return (
    <div className="MatchSmallCard">
      <p>
        {props.match.team1} vs {props.match.team2}
      </p>
    </div>
  );
};
