import React from "react";
import { MatchDetailsCard } from "../components/MatchDetailsCard";
import { MatchSmallCard } from "../components/MatchSmallCard";

export const TeamPage = () => {
  return (
    <div>
      <h1>Team Name</h1>
      <MatchDetailsCard />
      <MatchSmallCard />
      <MatchSmallCard />
      <MatchSmallCard />
    </div>
  );
};
