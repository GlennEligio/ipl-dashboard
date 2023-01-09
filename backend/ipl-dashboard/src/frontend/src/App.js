import "./App.css";
import { TeamPage } from "./pages/TeamPage";
import { HashRouter as Router, Route, Routes } from "react-router-dom";
import { MatchPage } from "./pages/MatchPage";
import { HomePage } from "./pages/HomePage";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/team/:teamName/matches/:year" element={<MatchPage />} />
          <Route path="/team/:teamName" element={<TeamPage />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
