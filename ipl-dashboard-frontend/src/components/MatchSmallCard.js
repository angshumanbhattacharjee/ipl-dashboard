import {React} from 'react';
import { Link } from 'react-router-dom';

export const MatchSmallCard = ({teamName, match}) => {
    const otherTeam = teamName === match.team1 ? match.team2 : match.team1;
    const otherTeamRoute = `/teams/${otherTeam}`;
    return (
        <div className="MatchSmallCard">
            <p>
                <h4>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h4>
                <h5>Played on {match.date} at {match.venue}</h5>
                <h5>{match.matchWinner} won by {match.resultMargin} {match.result}</h5>
            </p>
        </div>
    );
}