package com.learning.iplbackendapi.ipldashboardbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @GetMapping
    public ResponseEntity getLatestMatchesByTeamName (@PathVariable String teamName) {
        ResponseEntity responseEntity = null;
        return responseEntity;
    }
    
}
