import './App.css';
import {TeamPage} from './pages/TeamPage';
import {MatchPage} from './pages/MatchPage';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
        <Route path="/teams/:teamName/matches/:year">
          <MatchPage/>
        </Route>
        <Route path="/teams/:teamName">
          <TeamPage/>
        </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
