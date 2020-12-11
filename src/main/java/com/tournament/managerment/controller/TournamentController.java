package com.tournament.managerment.controller;

import com.tournament.managerment.dto.TournamentListDTO;
import com.tournament.managerment.service.TournamentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alltournament")
@Api(tags = "锦标赛相关")
@CrossOrigin
public class TournamentController {
    private final Logger logger = LoggerFactory.getLogger(TournamentController.class);

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService=tournamentService;
    }

    @ApiOperation("获取锦标赛列表")
    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TournamentListDTO> getTournaments() {
        TournamentListDTO tournamentList = tournamentService.getTournamentList();

        return ResponseEntity.ok(tournamentList);
    }
}
