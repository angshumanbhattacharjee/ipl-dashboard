package com.learning.iplbackendapi.ipldashboardbackend.controller;

import com.learning.iplbackendapi.ipldashboardbackend.service.MatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping(value = "/latest-matches/{teamName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLatestMatchesByTeamName (@PathVariable String teamName) {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(matchService.getAllMatchesByTeamName(teamName), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    
}
