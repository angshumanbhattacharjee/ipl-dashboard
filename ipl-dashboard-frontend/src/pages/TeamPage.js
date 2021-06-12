import {React, useEffect, useState} from 'react';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {MatchSmallCard} from '../components/MatchSmallCard';


export const TeamPage = () => {

    const [team, setTeam] = useState({latestMatches : []});

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch('http://localhost:8080/team/Kolkata Knight Riders');
                const data = await response.json();
                //console.log(data);
                setTeam(data);
            };
            fetchMatches();
        }, []
    );
    console.log(team);

    return (
        <div className="TeamPage">
            <h1>{team.teamName}</h1>
            <MatchDetailCard match={team.latestMatches[0]}/>
            {team !==null ? team.latestMatches.slice(1).map(match => <MatchSmallCard match={match} key={match.id}/>): <MatchSmallCard match={{}}/>}
        </div>
    );
}