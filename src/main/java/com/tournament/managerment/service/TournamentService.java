package com.tournament.managerment.service;

import com.tournament.managerment.dto.TournamentListDTO;

public interface TournamentService {
    //获取所有tournamentId以及对应的format
    TournamentListDTO getTournamentList();

}
