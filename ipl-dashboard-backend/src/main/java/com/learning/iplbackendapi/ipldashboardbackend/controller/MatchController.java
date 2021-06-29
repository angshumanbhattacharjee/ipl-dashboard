package com.learning.iplbackendapi.ipldashboardbackend.controller;

import java.util.NoSuchElementException;

import com.learning.iplbackendapi.ipldashboardbackend.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
@CrossOrigin (value = "http://localhost:3000")
public class MatchController {
    
    @Autowired
    TeamService teamService;

    @GetMapping(value = "/{teamName}/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMatchData (@PathVariable String teamName, @RequestParam int year) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(teamService.getMatchesByTeamAndYear(teamName, year), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            responseEntity = new ResponseEntity<>(("No matches for Team : " + teamName), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            throw e;
        }
        return responseEntity;
    }
}
