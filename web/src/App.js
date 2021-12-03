import './App.css';
import {BrowserRouter as Router, Route} from "react-router-dom";
import ProfitMaximizerPage from "./ProfitMaximizerPage";



function App() {
  return (
      <Router>
        <div style={{margin: "15px"}}>
          <Route exact path="/" component={ProfitMaximizerPage} />
        </div>
      </Router>
  );
}

export default App;
