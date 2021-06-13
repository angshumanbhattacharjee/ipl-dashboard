import {React, useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {MatchSmallCard} from '../components/MatchSmallCard';


export const TeamPage = () => {

    const [team, setTeam] = useState({latestMatches : []});
    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`);
                const data = await response.json();
                setTeam(data);
            };
            fetchMatches();
        }, [teamName]
    );
    console.log(team);

    if (!team || !team.teamName) {
        return (
            <h1> Team Not Found !!</h1>
        );
    }
    return (
        <div className="teamPage">
            <h1>{team.teamName}</h1>
            <MatchDetailCard teamName={team.teamName} match={team.latestMatches[0]}/>
            {team !==null ? team.latestMatches.slice(1).map(match => <MatchSmallCard teamName={team.teamName} match={match} key={match.id}/>): <MatchSmallCard teamName={{}} match={{}}/>}
        </div>
    );
}