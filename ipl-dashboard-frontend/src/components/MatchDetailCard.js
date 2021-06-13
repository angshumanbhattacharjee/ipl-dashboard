import {React} from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailCard = ({teamName, match}) => {
    const otherTeam = teamName === match.team1 ? match.team2 : match.team1;
    const otherTeamRoute = `/teams/${otherTeam}`;
    if (!match) {
        return null;
    }
    return (
        <div className="MatchDetailCard">
            <h2>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h2>
            <h3>Played on {match.date} at {match.venue}</h3>
            <h4>{match.matchWinner} won by {match.resultMargin} {match.result}</h4>
        </div>
    );
}