package com.learning.iplbackendapi.ipldashboardbackend.controller;

import java.util.NoSuchElementException;

import com.learning.iplbackendapi.ipldashboardbackend.model.TeamModel;
import com.learning.iplbackendapi.ipldashboardbackend.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping(value = "/{teamName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTeamData (@PathVariable String teamName) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(teamService.getTeamModel(teamName), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            responseEntity = new ResponseEntity<>(("No Team with Team Name : " + teamName), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            throw e;
        }
        return responseEntity;
    }
    
}
