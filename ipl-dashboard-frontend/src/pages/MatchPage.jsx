import {React, useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {MatchSmallCard} from '../components/MatchSmallCard';


export const MatchPage = () => {


    const [matches, setMatches] = useState([]);
    const { teamName, year } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/match/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data);
                console.log(data);
            };
            fetchMatches();
        }, [teamName, year]
    );

    if (!matches) {
        return (
            <h1> Matches Not Found !!</h1>
        );
    }

    return (
        <div className="matchPage">
            <h1>{teamName}</h1>
            {matches !== null ? (matches.map(match => <MatchDetailCard teamName={teamName} match={match} key={match.id}/>)): <MatchDetailCard teamName={{}} match={{}}/>}
        </div>
    );
}